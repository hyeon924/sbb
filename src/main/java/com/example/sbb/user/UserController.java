package com.example.sbb.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/signup")
    public String signup() {
        return "signup_form";
    }
}
