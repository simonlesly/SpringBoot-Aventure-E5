package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.AccessoireDao
import com.example.springaventure.model.dao.QualiteDao
import com.example.springaventure.model.dao.TypeAccessoireDao
import com.example.springaventure.model.entity.Accessoire
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AccessoireControleur (val accessoireDao: AccessoireDao,val typeAccessoireDao: TypeAccessoireDao,val qualiteDao: QualiteDao) {
    @GetMapping("/admin/accessoire")
    fun index(model: Model): String {
        val accessoires = this.accessoireDao.findAll()
        model.addAttribute("accessoires", accessoires)
        return "admin/accessoire/index"
    }

    @GetMapping("/admin/accessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val accessoire = this.accessoireDao.findById(id).orElseThrow()
        model.addAttribute("accessoire", accessoire)
        return "admin/accessoire/show"
    }

    @GetMapping("/admin/accessoire/create")
    fun create(model: Model): String {
        val nouvelleAccessoire = Accessoire(null, "", "", "")
        val types=typeAccessoireDao.findAll()
        val qualites=qualiteDao.findAll()
        model.addAttribute("types",types)
        model.addAttribute("qualites",qualites)
        model.addAttribute("nouvelleAccessoire", nouvelleAccessoire)
        return "admin/accessoire/create"
    }

    @PostMapping("/admin/accessoire")
    fun store(@ModelAttribute nouvelleAccessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        val savedAccessoire = this.accessoireDao.save(nouvelleAccessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.nom} réussi")
        return "redirect:/admin/accessoire"
    }

    @GetMapping("/admin/accessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val accessoire = this.accessoireDao.findById(id).orElseThrow()
        val types=typeAccessoireDao.findAll()
        val qualites=qualiteDao.findAll()
        model.addAttribute("types",types)
        model.addAttribute("qualites",qualites)
        model.addAttribute("accessoire", accessoire)
        return "admin/accessoire/edit"
    }

    @PostMapping("/admin/accessoire/update")
    fun update(@ModelAttribute accessoire: Accessoire, redirectAttributes: RedirectAttributes): String {
        val accessoireModifier = this.accessoireDao.findById(accessoire.id ?: 0).orElseThrow()
        accessoireModifier.nom = accessoire.nom
        accessoireModifier.qualite=accessoire.qualite
        accessoireModifier.cheminImage=accessoire.cheminImage
        accessoireModifier.description=accessoire.description
        accessoireModifier.typeAccessoire=accessoire.typeAccessoire
        val savedAccessoire = this.accessoireDao.save(accessoireModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom}TypeRessource$.nom réussie")
        return "redirect:/admin/accessoire"
    }

    @PostMapping("/admin/accessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val accessoire = this.accessoireDao.findById(id).orElseThrow()
        this.accessoireDao.delete(accessoire)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${accessoire.nom} réussie")
        return "redirect:/admin/accessoire"
    }

}