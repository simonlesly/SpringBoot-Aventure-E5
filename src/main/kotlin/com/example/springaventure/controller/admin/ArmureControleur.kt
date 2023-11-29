package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.ArmureDao
import com.example.springaventure.model.dao.QualiteDao
import com.example.springaventure.model.dao.TypeArmureDao
import com.example.springaventure.model.entity.Armure
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class ArmureControleur (val armureDao:ArmureDao,val qualiteDao: QualiteDao,val typeArmureDao: TypeArmureDao) {
    @GetMapping("/admin/armure")
    fun index(model: Model): String {
        val armures = this.armureDao.findAll()
        model.addAttribute("armures", armures)
        return "admin/armure/index"
    }

    @GetMapping("/admin/armure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val armure = this.armureDao.findById(id).orElseThrow()
        model.addAttribute("armure", armure)
        return "admin/armure/show"
    }

    @GetMapping("/admin/armure/create")
    fun create(model: Model): String {
        val nouvelleArmure = Armure(null, "", "","")
        val qualites= qualiteDao.findAll()
        val typesArmure = typeArmureDao.findAll()
        model.addAttribute("qualites",qualites)
        model.addAttribute("typesArmure",typesArmure)
        model.addAttribute("nouvelleArmure", nouvelleArmure)
        return "admin/armure/create"
    }

    @PostMapping("/admin/armure")
    fun store(@ModelAttribute nouvelleArmure: Armure, redirectAttributes: RedirectAttributes): String {
        val savedArmure = this.armureDao.save(nouvelleArmure)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedArmure.nom} réussi")
        return "redirect:/admin/armure"
    }

    @GetMapping("/admin/armure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val armure = this.armureDao.findById(id).orElseThrow()
        val qualites= qualiteDao.findAll()
        val typesArmure = typeArmureDao.findAll()
        model.addAttribute("qualites",qualites)
        model.addAttribute("typesArmure",typesArmure)
        model.addAttribute("armure", armure)
        return "admin/armure/edit"
    }

    @PostMapping("/admin/armure/update")
    fun update(@ModelAttribute armure: Armure, redirectAttributes: RedirectAttributes): String {
        val armureModifier = this.armureDao.findById(armure.id ?: 0).orElseThrow()
        armureModifier.nom = armure.nom
        armureModifier.qualite=armure.qualite
        armureModifier.cheminImage=armure.cheminImage
        armureModifier.description=armure.description
        armureModifier.typeArmure=armure.typeArmure
        // TODO: Mettez à jour d'autres champs si nécessaire
        val savedArmure = this.armureDao.save(armureModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArmure.nom}TypeRessource$.nom réussie")
        return "redirect:/admin/armure"
    }

    @PostMapping("/admin/armure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val armure = this.armureDao.findById(id).orElseThrow()
        this.armureDao.delete(armure)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${armure.nom} réussie")
        return "redirect:/admin/armure"
    }

}