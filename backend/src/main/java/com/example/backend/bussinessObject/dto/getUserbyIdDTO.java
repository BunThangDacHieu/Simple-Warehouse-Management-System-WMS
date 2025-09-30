package com.example.backend.bussinessObject.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class getUserbyIdDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
