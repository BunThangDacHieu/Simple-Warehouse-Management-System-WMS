package com.example.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.backend.bussinessObject.dto.getUserbyIdDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.bussinessObject.model.User;
import com.example.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*-------------------------------------Util-------------------------------------*/
    @GetMapping("/get-count-role")
    public ResponseEntity<Map<User.Role, Long>> getCountRole(){
        try {
            return ResponseEntity.ok(userService.countByUserRole());
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /*---------------------------------CRUD cơ bản--------------------------------- */
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<getUserbyIdDTO> getUserById(@Valid @PathVariable int id) {
        try {
            getUserbyIdDTO users = userService.findUserbyId(id);
            return ResponseEntity.ok(new getUserbyIdDTO(
                    users.getId(),
                    users.getName(),
                    users.getEmail(),
                    users.getAddress(),
                    users.getPhone()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updatedUser(@Valid @PathVariable int id, @RequestBody User user) {
        try {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*---------------------------------logic nghiệp vụ---------------------------- */
    //Manager Service
    //Supplier Service
    //Customer Service
}
