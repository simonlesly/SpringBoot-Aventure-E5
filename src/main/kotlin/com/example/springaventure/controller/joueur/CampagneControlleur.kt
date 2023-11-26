package com.example.springaventure.controller.joueur

import com.example.springaventure.model.dao.CampagneDao
import com.example.springaventure.model.dao.PersonnageDao
import com.example.springaventure.model.entity.Combat
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class CampagneControlleur (val campagneDao: CampagneDao,val personnageDao: PersonnageDao) {
    @GetMapping("/joueur/campagne")
    fun index(model: Model):String {
        // Récupérer l'objet Principal
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        // Récupérer le nom d'utilisateur à partir de l'objet Principal
        val email: String = authentication.getName()
        val lesCampagnes= this.campagneDao.findByUtilisateur_EmailIgnoreCase(email)
        model.addAttribute("campagnes",lesCampagnes)
        return "joueur/campagne/index"
    }

    @GetMapping("/joueur/campagne/{id}/play")
    fun play(@PathVariable id:Long, model: Model):String{
      //TODO verification
        var campagne = this.campagneDao.findById(id).orElseThrow()
        var hero=campagne!!.hero
        hero!!.pointDeVie=campagne.hero!!.pointDeVieMax
        hero=personnageDao.save(hero)
        for(  combat in campagne.combats  ){
            val monstre=combat.monstre
            monstre!!.pointDeVie=monstre.pointDeVieMax
            personnageDao.save(monstre)
        }
        campagne.statut="En cours"
        campagne=campagneDao.save(campagne)
        val leCombat: Combat =campagne.combats.find { combat -> combat.estTerminer==false  } ?: campagne.combats.last()
        //TODO Campagne terminer
        model.addAttribute("combat",leCombat)
        return "joueur/combat/index"
    }
}