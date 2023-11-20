package com.example.springaventure.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
@Controller
class ArmeControlleur(armeDao: ArmeDao) {

    @GetMapping("/acteur/ressource")
    fun index(model: Model): String {
        val ressources = this.ArmeDao.findAll()
        model.addAttribute("ressources", ressources)
        return "acteur/ressource/index"
    }

    @GetMapping("/acteur/ressource/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val ressource = this.ArmeDao.findById(id).orElseThrow()
        model.addAttribute("ressource", ressource)
        return "acteur/ressource/show"
    }

    @GetMapping("/acteur/ressource/create")
    fun create(model: Model): String {
        val nouvelle = Ressource(null, "", "", 0)
        model.addAttribute("nouvelle", nouvelle)
        return "acteur/ressource/create"
    }

    @PostMapping("/acteur/ressource")
    fun store(@ModelAttribute nouvelle: Ressource, redirectAttributes: RedirectAttributes): String {
        val saved = this.ArmeDao.save(nouvelle)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de \${saved.nom} réussi")
        return "redirect:/acteur/ressource"
    }

    @GetMapping("/acteur/ressource/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val ressource = this.ArmeDao.findById(id).orElseThrow()
        model.addAttribute("ressource", ressource)
        return "acteur/ressource/edit"
    }

    @PostMapping("/acteur/ressource/update")
    fun update(@ModelAttribute ressource: Ressource, redirectAttributes: RedirectAttributes): String {
        val ressourceModifier = this.ArmeDao.findById(ressource.id ?: 0).orElseThrow()

        //Valorisé les attributs de ressourceModifier ex : ressourceModifier.nom = ressource.nom
        ressourceModifier.bonus = ressource.bonus
        val saved = this.ArmeDao.save(ressourceModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de \${saved.nom} réussie")
        return "redirect:/acteur/ressource"
    }

    @PostMapping("/acteur/ressource/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val ressource = this.ArmeDao.findById(id).orElseThrow()
        this.ArmeDao.delete(ressource)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de \${ressource.nom} réussie")
        return "redirect:/acteur/ressource"
    }
}
