package com.example.springaventure.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainControlleur {
    @GetMapping("/login")
    fun login(@RequestParam error: Boolean?, model: Model): String {
        // Ajoute un attribut "error" au modèle si la requête contient une erreur
        model.addAttribute("error", error == true)
        return "visiteur/login"
    }
}