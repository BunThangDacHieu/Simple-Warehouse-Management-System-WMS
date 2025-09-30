package com.example.backend.controller;

import java.util.Map;
import java.util.UUID;

import com.example.backend.bussinessObject.dto.AuthResponseDTO;
import com.example.backend.bussinessObject.dto.ErrorResponse;
import com.example.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.backend.service.MailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.backend.bussinessObject.dto.AuthResponseDTO;
import com.example.backend.bussinessObject.model.User;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final String SiteURL = "http://localhost:4200";

    public AuthController(
            UserService userService,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            MailService mailService
    ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }
    /*----------------Bộ ba đăng nhập đăng ký, và làm mới token----------------------*/
    //Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            if (userService.findUserbyEmail(user) != null) {
                return ResponseEntity.badRequest().body("User already exists");
            }
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            if(userService.findUserbyEmail(user) == null) {
                return ResponseEntity.status(401).body(new ErrorResponse("Người dùng không tồn tại, mời dùng tài khoản mới hoặc đăng ký tài khoản mới", 401));
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String accessToken = jwtUtil.generateToken(userDetails, 15 * 60 * 1000L);
            String refreshToken = jwtUtil.generateToken(userDetails, 30 * 24 * 60 * 60 * 1000L);
            return ResponseEntity.ok(new AuthResponseDTO(accessToken, refreshToken));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new ErrorResponse("Đăng nhập thất bại", 401));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            UserDetails userDetails = userService.loadUserByUsername(username);
            String newAccessToken = jwtUtil.generateToken(userDetails, 15 * 60 * 1000L);
            return ResponseEntity.ok(new AuthResponseDTO(newAccessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /*-------------------------------------Quên mật khẩu---------------------------*/
    //Quên mật khẩu
    @PostMapping("/forgot_password")
    public String forgotPassword(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //Nhập địa chỉ email để đăng ký
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            userService.ResetPasswordbyToken(token, email);
            User user = userService.getByResetToken(token);
            mailService.voidResetPasswordEmail(user, SiteURL);
            redirectAttributes.addAttribute("token", token);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return "/forgot_password";
    }

    @GetMapping("/reset_password")
    public String resetPassword(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String password = request.getParameter("password");
        String token = request.getParameter("token");
        String confirmPassword = request.getParameter("confirmPassword");

        if(!password.equals(confirmPassword)){
            redirectAttributes.addFlashAttribute("error", "Mật khẩu không khớp");
            return "redirect:/login";
        }
        User user = userService.getByResetToken(token);
        if(user == null){
            redirectAttributes.addFlashAttribute("error", "Token không hợp lệ");
            return "redirect:/login";
        }
        try{
            userService.updatePassword(user, password);
            redirectAttributes.addFlashAttribute("message", "Bạn đã thay đổi mật khẩu thành công");
            return "redirect:/login";
        } catch(Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/reset_password?token=" + token;
        }
    }
}
