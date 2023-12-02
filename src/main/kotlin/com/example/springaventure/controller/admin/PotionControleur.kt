package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.PotionDao
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class PotionControleur (val potionDao: PotionDao) {
    @GetMapping("/admin/potion")
    fun index(model: Model): String {
        val potions = this.potionDao.findAll()
        model.addAttribute("potions", potions)
        // Retourne le nom de la vue à afficher
        return "admin/potion/index"
    }
}