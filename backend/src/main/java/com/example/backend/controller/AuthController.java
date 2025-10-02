package com.example.backend.controller;

import java.util.*;

import com.example.backend.bussinessObject.dto.*;
import com.example.backend.bussinessObject.model.Customer;
import com.example.backend.bussinessObject.model.Supplier;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.repository.SupplierRepository;
import com.example.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import com.example.backend.service.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
import com.example.backend.bussinessObject.model.User;
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
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final String SiteURL = "http://localhost:4200";

    public AuthController(
            UserService userService,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            MailService mailService,
            CustomerRepository customerRepository,
            SupplierRepository supplierRepository
    ) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
    }
    /*----------------Bộ ba đăng nhập đăng ký, và làm mới token----------------------*/
    //Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Map<String, Object> requestData) {
        try {
            String role = (String) requestData.get("role");

            if(role == null) {
                return ResponseEntity.badRequest().body("Role is required");
            }
            String email = (String) requestData.get("email");
            User existingUser = userRepository.findByEmail(email);
            if(existingUser != null) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Email đã tồn tại");
                return ResponseEntity.badRequest().body(errorResponse);
            }
            User savedUser;
            if("CUSTOMER".equals(role)){
                Customer customer = new Customer();
                customer.setName((String) requestData.get("name"));
                customer.setEmail((String) requestData.get("email"));
                customer.setPassword((String) requestData.get("password"));
                customer.setRole(User.Role.CUSTOMER);
                savedUser = userService.saveUser(customer);
            } else if("SUPPLIER".equals(role)){
                Supplier supplier = new Supplier();
                supplier.setName((String) requestData.get("name"));
                supplier.setEmail((String) requestData.get("email"));
                supplier.setPassword((String) requestData.get("password"));
                supplier.setAddress((String) requestData.get("address"));
                supplier.setPhone((String) requestData.get("phone"));
                supplier.setContract_person((String) requestData.get("contract_person"));
                supplier.setRole(User.Role.SUPPLIER);
                savedUser = userService.saveUser(supplier);
            } else{
                return ResponseEntity.badRequest().body("Invalid role");
            }
            return ResponseEntity.ok(savedUser);
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
