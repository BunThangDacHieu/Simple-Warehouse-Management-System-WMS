package com.example.backend.controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CookieController {
    @GetMapping
    public String setCustomCookie() {
        return "Hello World";
    }
}
