package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Tìm dựa trên id
    public Optional<User> findUserbyId(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
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
        userRepository.deleteById(id);
    }
    /*-------------------------------------Logic nghiệp vụ------------------------------------- */
}
