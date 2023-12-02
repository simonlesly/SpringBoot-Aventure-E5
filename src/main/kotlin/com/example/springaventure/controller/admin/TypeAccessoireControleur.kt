package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.TypeAccessoireDao
import com.example.springaventure.model.entity.TypeAccessoire
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeAccessoireControleur (val typeAccessoireDao: TypeAccessoireDao) {
    @GetMapping("/admin/typeAccessoire")
    fun index(model: Model): String {
        val typeAccessoires = this.typeAccessoireDao.findAll()
        model.addAttribute("typeAccessoires", typeAccessoires)
        return "admin/typeaccessoire/index"
    }

    @GetMapping("/admin/typeAccessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val typeAccessoire = this.typeAccessoireDao.findById(id).orElseThrow()
        model.addAttribute("typeAccessoire", typeAccessoire)
        return "admin/typeaccessoire/show"
    }

    @GetMapping("/admin/typeAccessoire/create")
    fun create(model: Model): String {
        val nouvelleAccessoire = TypeAccessoire(null,"","")
        model.addAttribute("nouveauTypeAccessoire", nouvelleAccessoire)
        return "admin/typeAccessoire/create"
    }

    @PostMapping("/admin/typeAccessoire")
    fun store(@ModelAttribute nouvelleAccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val savedAccessoire = this.typeAccessoireDao.save(nouvelleAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.nom} réussi")
        return "redirect:/admin/typeAccessoire"
    }

    @GetMapping("/admin/typeAccessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val typeAccessoire = this.typeAccessoireDao.findById(id).orElseThrow()
        model.addAttribute("typeAccessoire", typeAccessoire)
        return "admin/typeAccessoire/edit"
    }

    @PostMapping("/admin/typeAccessoire/update")
    fun update(@ModelAttribute typeAccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        val typeAccessoireModifier = this.typeAccessoireDao.findById(typeAccessoire.id ?: 0).orElseThrow()
        typeAccessoireModifier.nom = typeAccessoire.nom
        typeAccessoireModifier.typeBonus = typeAccessoire.typeBonus
        val savedAccessoire = this.typeAccessoireDao.save(typeAccessoireModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom} réussie")
        return "redirect:/admin/typeAccessoire"
    }

    @PostMapping("/admin/typeAccessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val typeAccessoire = this.typeAccessoireDao.findById(id).orElseThrow()
        this.typeAccessoireDao.delete(typeAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeAccessoire.nom} réussie")
        return "redirect:/admin/typeAccessoire"
    }

}