package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.QualiteDAO
import com.example.springaventure.model.entity.Qualite
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class QualiteControlleur(val qualiteDao: QualiteDAO) {

    @GetMapping("/admin/qualite")
    fun index(model: Model): String {
        val qualites = this.qualiteDao.findAll()
        model.addAttribute("qualites", qualites)
        return "admin/qualite/index"
    }

    @GetMapping("/admin/qualite/{id}")
    fun show (@PathVariable id:Long, model:Model):String{
        val uneQualite=this.qualiteDao.findById(id)
        model.addAttribute("qualite",uneQualite)
        return "admin/qualite/show"
    }

    @GetMapping("/admin/qualite/create")
    fun create(model: Model):String{
        val nouvelleQualite= Qualite(null,"","",0)
        model.addAttribute("nouvelleQualite",nouvelleQualite)
        return "admin/qualite/create"
    }
}