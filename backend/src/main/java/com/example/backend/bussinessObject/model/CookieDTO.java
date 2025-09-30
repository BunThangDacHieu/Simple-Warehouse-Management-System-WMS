package com.example.backend.bussinessObject.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CookieDTO {
    // Thuộc tính bắt buộc
    private String name;
    private String value;

    //Thuộc tính vòng đời và bảo mật
    private int maxAge = -1;
    private boolean secure = false;
    private boolean httpOnly = true;
    private String sameSite = "Lax";

    //Thuộc tính phạm vi kiểm soát
    private String path = "/";
    private String domain;
}
