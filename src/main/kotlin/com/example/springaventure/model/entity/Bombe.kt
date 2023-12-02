package com.example.springaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("bombe")
class Bombe(
    id: Long,
    nom: String,
    description: String,
    cheminImage: String,
    var nbrDes: Int,
    var maxDes: Int,
) : Item(id, nom, description, cheminImage) {
    override fun utiliser(cible:Personnage):String{
        val deDegat = TirageDes(nbrDes, maxDes)
        var degat = deDegat.lance()
        degat = maxOf(1, degat - cible.calculeDefense())
        cible.pointDeVie -= degat
        return ("La/Le ${this.nom} inflige $degat points de dégâts<br>")
    }
}