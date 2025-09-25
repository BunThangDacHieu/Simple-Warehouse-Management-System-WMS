package com.example.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.util.ObjectValidator;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ObjectValidator objectValidator;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    //Xây dựng user
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        Map<String, String> error = objectValidator.getRequestAndSummitErrors(user);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole() != null ? user.getRole().name() : "CUSTOMER")
                .build();

    }

    //Lưu người dùng với mật khẩu được mã hóa
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<User> findUserbyId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User id must be greater than 0");
        }
        return userRepository.findById(id);
    }

    //Cập nhật
    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setName(updatedUser.getName());
        user.setAddress(updatedUser.getAddress());
        user.setPhone(updatedUser.getPhone());
        user.setContract_person(updatedUser.getContract_person());
        return userRepository.save(user);
    }

    //Xóa
    public void deleteUser(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User id must be greater than 0");
        }
        userRepository.deleteById(id);
    }

    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
    //Auth Service
    public User findUserbyEmail(User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    //Count User
    public Map<User.Role, Long> countByUserRole(){
        //Khởi tạo count với kiểu dữ liệu là Map, và sử dụng HashMap để tạo dạng cột với dạng dữ liệu ở phía trước là key là Role lấy từ phía role của đối tượng User
        //Trong Long là Value cùng với khi khởi tạo thì bắt đầu trả về
        Map<User.Role, Long> count = new HashMap<>();
        for(User.Role role: User.Role.values()){
            count.put(role, userRepository.countUserByRole(role));
        }
        return count;
    }
}
