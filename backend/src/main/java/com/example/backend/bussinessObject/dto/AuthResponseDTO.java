package com.example.backend.bussinessObject.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDTO {
    private final String accessToken;
    private final String refreshToken;
}

