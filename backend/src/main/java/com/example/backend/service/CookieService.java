package com.example.backend.service;

import com.example.backend.bussinessObject.model.CookieDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {
    public void getCookieForResponse(HttpServletResponse response, CookieDTO cookieDTO) {
        //Khai báo cookie với dữ liệu và giá trị nhận từ cookieDTO
        Cookie cookie = new Cookie(cookieDTO.getName(), cookieDTO.getValue());

        //Thiết lập các thuộc tính của cookie
        cookie.setMaxAge(cookieDTO.getMaxAge());
        cookie.setPath(cookieDTO.getPath());

        if (cookieDTO.getDomain() != null) {
            cookie.setDomain(cookieDTO.getDomain());
        }

        cookie.setSecure(cookieDTO.isSecure());
        cookie.setHttpOnly(cookieDTO.isHttpOnly());

        //Áp dụng với Header
        String sameSiteValue = cookieDTO.getSameSite() != null ? cookieDTO.getSameSite() : "Lax";
        // Tạo giá trị header Set-Cookie với các thuộc tính cần thiết
        String headerValue = String.format("%s=%s; Max-Age=%d; Path=%s; %s %s SameSite=%s",
                cookie.getName(),
                cookie.getValue(),
                cookie.getMaxAge(),
                cookie.getPath(),
                (cookie.getDomain() != null ? "Domain=" + cookie.getDomain() + ";" : ""),
                (cookie.getSecure() ? "Secure;" : ""),
                sameSiteValue
        );
        response.addHeader("Set-Cookie", headerValue);
    }
}
