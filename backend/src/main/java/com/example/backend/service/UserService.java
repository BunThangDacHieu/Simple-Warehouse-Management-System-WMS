package com.example.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.backend.bussinessObject.dto.getUserbyIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.util.ObjectValidator;
import com.example.backend.bussinessObject.model.User;
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
    /*---------------------------------Autherization----------------------------*/
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
        user.setPassword(passwordEncoder.encode(user.getPassword())); //
        return userRepository.save(user);
    }
    /*-------------------------------------------Password Reset ----------------------------------------*/
    //Tìm kiếm người dùng dựa trên email, đồng thời thực hiện thay đổi trên cơ sở dữ liệu tại thuộc tính ResetPasswordToken của User
    public void ResetPasswordbyToken(String token, String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email); //Tìm kiếm người dugnf dựa trên email
        if (user != null) { //Nếu người dùng tồn tại
            user.setResetPasswordToken(token); //Cập nhật dữ liệu của thuộc tính ResetPasswordToken
            userRepository.save(user); //Lưu thay đổi tại bảng User
        } else { // Nếu người dùng không tồn tại
            throw new UsernameNotFoundException("User not found" + email); //trả lại thông báo rằng tên người dùng không tìm thấy
        }
    }

    public User getByResetToken(String token) { //TÌm kiếm người dùng thông qua token
        //Nếu token không tồn tại như vậy là người dùng không hợp lệ
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword){ //Cập nhật mật khẩu cho người dùng với mật khẩu mới
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //Thông qua thư viện để gọi việc mã hóa với tên encoder
        if(encoder.matches(newPassword, user.getPassword())){ //Kiểm tra xem mật khẩu cũ có đúng với mật khẩu mới không? Nếu có thì thông báo trùng mật khẩu
            throw new RuntimeException("Password can't be the same");
        } else {
            String encoderPassword =  encoder.encode(newPassword); //Khai báo encoderPassword để chứa thông tin dữ liệu mà encoder đã mã đối với mật khẩu mới
            user.setPassword(encoderPassword); //Cập nhật mật khẩu đã được mã háo vào trong cơ sở dữ liệu
            user.setResetPasswordToken(null); // cập nhật lại Token để reset password để trống
            userRepository.save(user); //Lưu thông tin mới của người dùng
        }
    }
    /*-------------------------------------CRUD cơ bản------------------------------------- */
    //Lấy danh sách của toàn bộ hàng hóa
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //Tìm dựa trên id
    public getUserbyIdDTO findUserbyId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("User id must be greater than 0");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tìm không thấy user"));

        return new getUserbyIdDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getPhone()
        );
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
