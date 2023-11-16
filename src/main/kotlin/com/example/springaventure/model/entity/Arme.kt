package com.example.springaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
@DiscriminatorValue("arme")
class Arme constructor(
    id: Long,
    nom: String,
    description: String,
    cheminImage: String?,
//TODO Attributs spécifiques aux armes
    //Association entre Arme et Qualite
    //Plusieurs armes peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,
    //TODO Faire l'association avec TypeArme
) : Item(id, nom, description, cheminImage) {


}