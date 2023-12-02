package com.example.springaventure.controller.joueur

import com.example.springaventure.model.dao.PersonnageDao
import com.example.springaventure.model.dao.UtilisateurDao
import com.example.springaventure.model.entity.Personnage
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class PersonnageControleur(
    val personnageDao: PersonnageDao,
    val utilisateurDao: UtilisateurDao
) {
@GetMapping("/joueur/personnage")
fun index(model: Model): String {
    // Récupérer l'objet Principal
    val authentication: Authentication = SecurityContextHolder.getContext().authentication
    // Récupérer le nom d'utilisateur à partir de l'objet Principal
    val email: String = authentication.getName()
    //Récupérer l'utilisateur
    val utilisateur=utilisateurDao.findByEmail(email)!!

    val personnages = this.personnageDao.findByUtilisateur_IdOrderByIdDesc(utilisateur.id!!)

    model.addAttribute("personnages", personnages)
    return "joueur/personnage/index"
}

@GetMapping("/joueur/personnage/{id}")
fun show(@PathVariable id: Long, model: Model): String {
    val personnage = this.personnageDao.findById(id).orElseThrow()
    model.addAttribute("personnage", personnage)
    return "joueur/personnage/show"
}

@GetMapping("/joueur/personnage/create")
fun create(model: Model): String {
    val nouvellePersonnage = Personnage(null, "", 1, 6, 2, 20)
    model.addAttribute("nouvellePersonnage", nouvellePersonnage)
    return "joueur/personnage/create"
}

@PostMapping("/joueur/personnage")
fun store(@ModelAttribute nouvellePersonnage: Personnage, redirectAttributes: RedirectAttributes): String {
    // Récupérer l'objet Principal
    val authentication: Authentication = SecurityContextHolder.getContext().authentication
    // Récupérer le nom d'utilisateur à partir de l'objet Principal
    val email: String = authentication.getName()
    //Récupérer l'utilisateur
    val utilisateur=utilisateurDao.findByEmail(email)!!
    nouvellePersonnage.utilisateur=utilisateur
    val savedPersonnage = this.personnageDao.save(nouvellePersonnage)
    redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedPersonnage.nom} réussi")
    return "redirect:/joueur/personnage"
}

@GetMapping("/joueur/personnage/{id}/edit")
fun edit(@PathVariable id: Long, model: Model): String {
    val personnage = this.personnageDao.findById(id).orElseThrow()
    model.addAttribute("personnage", personnage)
    return "joueur/personnage/edit"
}

@PostMapping("/joueur/personnage/update")
fun update(@ModelAttribute personnage: Personnage, redirectAttributes: RedirectAttributes): String {
    val personnageModifier = this.personnageDao.findById(personnage.id ?: 0).orElseThrow()
    // Récupérer l'objet Principal
    val authentication: Authentication = SecurityContextHolder.getContext().authentication
    // Récupérer le nom d'utilisateur à partir de l'objet Principal
    val email: String = authentication.getName()
    //Récupérer l'utilisateur
    val utilisateur=utilisateurDao.findByEmail(email)!!

    personnageModifier.nom = personnage.nom
    personnageModifier.attaque=personnage.attaque
    personnageModifier.defense=personnage.defense
    personnageModifier.endurance=personnage.endurance
    personnageModifier.vitesse=personnage.vitesse
    personnageModifier.utilisateur=utilisateur
    val savedPersonnage = this.personnageDao.save(personnageModifier)
    redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedPersonnage.nom}TypeRessource$.nom réussie")
    return "redirect:/joueur/personnage"
}

@PostMapping("/joueur/personnage/delete")
fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
    val personnage = this.personnageDao.findById(id).orElseThrow()
    this.personnageDao.delete(personnage)
    redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${personnage.nom} réussie")
    return "redirect:/joueur/personnage"
}

}