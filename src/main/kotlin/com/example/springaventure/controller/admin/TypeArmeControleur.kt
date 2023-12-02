package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.TypeArmeDao
import com.example.springaventure.model.entity.TypeArme
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeArmeControleur(val typeArmeDao: TypeArmeDao) {

    @GetMapping("/admin/typeArme")
    fun index(model: Model): String {
        val typeArmes = this.typeArmeDao.findAll()
        model.addAttribute("typeArmes", typeArmes)
        return "admin/typearme/index"
    }

    @GetMapping("/admin/typeArme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val typeArme = this.typeArmeDao.findById(id).orElseThrow()
        model.addAttribute("typeArme", typeArme)
        return "admin/typearme/show"
    }

    @GetMapping("/admin/typeArme/create")
    fun create(model: Model): String {
        val nouvelleTypeArme = TypeArme(null, "", 1, 6,2,20)
        model.addAttribute("nouvelleTypeArme", nouvelleTypeArme)
        return "admin/typearme/create"
    }

    @PostMapping("/admin/typeArme")
    fun store(@ModelAttribute nouvelleTypeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        val savedTypeArme = this.typeArmeDao.save(nouvelleTypeArme)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeArme.nom} réussi")
        return "redirect:/admin/typeArme"
    }

    @GetMapping("/admin/typeArme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val typeArme = this.typeArmeDao.findById(id).orElseThrow()
        model.addAttribute("typeArme", typeArme)
        return "admin/typearme/edit"
    }

    @PostMapping("/admin/typeArme/update")
    fun update(@ModelAttribute typeArme: TypeArme, redirectAttributes: RedirectAttributes): String {
        val typeArmeModifier = this.typeArmeDao.findById(typeArme.id ?: 0).orElseThrow()
        typeArmeModifier.nom = typeArme.nom
       typeArmeModifier.nbrDes=typeArme.nbrDes
        typeArmeModifier.maxDes=typeArme.maxDes
        typeArmeModifier.limiteCritique=typeArme.limiteCritique
        typeArmeModifier.mutiplicateurCritique=typeArme.mutiplicateurCritique
        val savedTypeArme = this.typeArmeDao.save(typeArmeModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedTypeArme.nom} réussie")
        return "redirect:/admin/typeArme"
    }

    @PostMapping("/admin/typeArme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val typeArme = this.typeArmeDao.findById(id).orElseThrow()
        this.typeArmeDao.delete(typeArme)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeArme.nom} réussie")
        return "redirect:/admin/typeArme"
    }
}
