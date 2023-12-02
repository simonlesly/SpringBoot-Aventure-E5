package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.BombeDao
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class BombeControleur (val bombeDao: BombeDao) {
    @GetMapping("/admin/bombe")
    fun index(model: Model): String {
        val bombes = this.bombeDao.findAll()
        model.addAttribute("bombes", bombes)
        // Retourne le nom de la vue à afficher
        return "admin/bombe/index"
    }
}