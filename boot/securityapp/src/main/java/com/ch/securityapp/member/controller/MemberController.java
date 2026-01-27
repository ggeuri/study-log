package com.ch.securityapp.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/")
    public String getMain(){
        return "지금 지쳤나요?";
    }
}
