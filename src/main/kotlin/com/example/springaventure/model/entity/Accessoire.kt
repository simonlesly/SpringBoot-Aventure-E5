package com.example.springaventure.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Accessoire(
    id: Long?,
    nom: String,
    description: String,
    cheminImage: String,
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,
    @ManyToOne
    @JoinColumn(name = "type_accessoire_id")
    var typeAccessoire: TypeAccessoire? = null,
    @OneToMany(mappedBy = "accessoire", orphanRemoval = true)
    var personnages: MutableList<Personnage> = mutableListOf()
) : Item(id, nom, description, cheminImage) {



    override fun utiliser(cible: Personnage): String {
        TODO("Not yet implemented")
    }
}