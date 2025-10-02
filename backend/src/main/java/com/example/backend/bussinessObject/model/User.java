package com.example.backend.bussinessObject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Min(value = 1, message = "ID must be greater than 0")
    private int id;
    @NotNull
    @Size(min = 10, max = 20, message = "Tên phải có ít nhất 10 chữ cái, đầy đủ tên")
    private String name;
    @NotNull
    @Email(message = "Cần đúng định dạng Email")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
            message = "Mật khẩu cần bao gồm chữ cái viết Hoa, chữ thường, số, và kí tự đặc biệt và có độ dài ít nhất là 8"
    )
    private String password;
    @Enumerated(EnumType.STRING)
    public Role role;

    public enum Role {
        SUPPLIER,
        MANAGER,
        CUSTOMER
    }
    @Size(min = 10, max = 20, message = "Tên của người liên hệ cần ít nhất 10 chữ cái, và đầy đủ họ và tên")
    private String contract_person;
    @Pattern(regexp = "^0[0-9]{9}$", message="Cần bắt đầu bằng số 0")
    private String phone;
    @Pattern(
            regexp = "^(?=.*\\p{L})[\\p{L}0-9 ,.-]+$",
            message = "Địa chỉ phải chính xác"
    )
    private String address;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

}
