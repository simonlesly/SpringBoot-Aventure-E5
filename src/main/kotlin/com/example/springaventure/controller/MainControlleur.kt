package com.example.springaventure.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainControlleur {
    @GetMapping("/login")
    fun login():String{
        return "visiteur/login"
    }
}