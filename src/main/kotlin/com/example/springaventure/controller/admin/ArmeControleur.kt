package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.ArmeDao
import com.example.springaventure.model.dao.QualiteDao
import com.example.springaventure.model.dao.TypeArmeDao
import com.example.springaventure.model.entity.Arme
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
@Controller
class ArmeControleur(val armeDao: ArmeDao, val qualiteDao: QualiteDao, val typeArmeDao: TypeArmeDao) {

    @GetMapping("/acteur/ressource")
    fun index(model: Model): String {
        val ressources = this.armeDao.findAll()
        model.addAttribute("armes", ressources)
        return "admin/arme/index"
    }

    @GetMapping("/acteur/ressource/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        val ressource = this.armeDao.findById(id).orElseThrow()
        model.addAttribute("arme", ressource)
        return "admin/arme/show"
    }

    @GetMapping("/acteur/ressource/create")
    fun create(model: Model): String {
        val nouvelle = Arme(null, "", "", "")
        val qualites= qualiteDao.findAll()
        val typesArmes = typeArmeDao.findAll()
        model.addAttribute("qualites",qualites)
        model.addAttribute("typesArmes",typesArmes)
        model.addAttribute("arme", nouvelle)
        return "admin/arme/create"
    }

    @PostMapping("/acteur/ressource")
    fun store(@ModelAttribute nouvelle: Arme, redirectAttributes: RedirectAttributes): String {
        val saved = this.armeDao.save(nouvelle)
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${saved.nom} réussi")
        return "redirect:/admin/arme"
    }

    @GetMapping("/acteur/ressource/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        val ressource = this.armeDao.findById(id).orElseThrow()
        val qualites= qualiteDao.findAll()
        val typesArmes = typeArmeDao.findAll()
        model.addAttribute("qualites",qualites)
        model.addAttribute("typesArmes",typesArmes)
        model.addAttribute("ressource", ressource)
        return "admin/arme/edit"
    }

    @PostMapping("/acteur/ressource/update")
    fun update(@ModelAttribute arme: Arme, redirectAttributes: RedirectAttributes): String {
        val armeModifier = this.armeDao.findById(arme.id ?: 0).orElseThrow()

        //Valorisé les attributs de ressourceModifier ex : ressourceModifier.nom = ressource.nom
        armeModifier.nom = arme.nom
        armeModifier.description = arme.description
        armeModifier.typeArme =arme.typeArme
        armeModifier.qualite = arme.qualite
        armeModifier.cheminImage =armeModifier.cheminImage
        val saved = this.armeDao.save(armeModifier)
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${saved.nom} réussie")
        return "redirect:/admin/arme"
    }

    @PostMapping("/acteur/ressource/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        val arme = this.armeDao.findById(id).orElseThrow()
        this.armeDao.delete(arme)
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${arme.nom} réussie")
        return "redirect:/admin/arme"
    }
}
