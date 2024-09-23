package com.example.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test page";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/article/list";
    }
}
