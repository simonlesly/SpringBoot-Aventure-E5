package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.TypeArmeDao
import com.example.springaventure.model.dao.TypeArmureDao
import com.example.springaventure.model.entity.TypeArmure
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeArmureControleur(val typeArmureDao: TypeArmureDao){
    @GetMapping("/admin/typeArmure")
    fun index(model: Model): String {
        val typeArmures = this.typeArmureDao.findAll()
        model.addAttribute("typeArmures", typeArmures)
        return "admin/typeArmure/index"
    }

    @GetMapping("/admin/typeArmure/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val typeArmure = this.typeArmureDao.findById(id).orElseThrow()
        model.addAttribute("typeArmure", typeArmure)
        return "admin/typeArmure/show"
    }

    @GetMapping("/admin/typeArmure/create")
    fun create(model: Model): String {
        val nouvelleTypeArmure = TypeArmure(null, "", 1)
        model.addAttribute("nouvelleTypeArmure", nouvelleTypeArmure)
        return "admin/typeArmure/create"
    }

    @PostMapping("/admin/typeArmure")
    fun store(@ModelAttribute nouvelleTypeArmure: TypeArmure, redirectAttributes: RedirectAttributes): String {
        val savedTypeArmure = this.typeArmureDao.save(nouvelleTypeArmure)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeArmure.nom} réussi")
        return "redirect:/admin/typeArmure"
    }

    @GetMapping("/admin/typeArmure/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val typeArmure = this.typeArmureDao.findById(id).orElseThrow()
        model.addAttribute("typeArmure", typeArmure)
        return "admin/typeArmure/edit"
    }

    @PostMapping("/admin/typeArmure/update")
    fun update(@ModelAttribute typeArmure: TypeArmure, redirectAttributes: RedirectAttributes): String {
        val typeArmureModifier = this.typeArmureDao.findById(typeArmure.id ?: 0).orElseThrow()
        typeArmureModifier.nom = typeArmure.nom
        typeArmureModifier.bonusType = typeArmure.bonusType

        val savedTypeArmure = this.typeArmureDao.save(typeArmureModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedTypeArmure.nom} réussie")
        return "redirect:/admin/typeArmure"
    }

    @PostMapping("/admin/typeArmure/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val typeArmure = this.typeArmureDao.findById(id).orElseThrow()
        this.typeArmureDao.delete(typeArmure)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArmure.nom} réussie")
        return "redirect:/admin/typeArmure"
    }

}