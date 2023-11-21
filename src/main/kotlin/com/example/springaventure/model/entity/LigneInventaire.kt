package com.example.springaventure.model.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
class LigneInventaire(
    @EmbeddedId
    var ligneInventaireId: LigneInventaireId? = null,

    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    @MapsId("personnageId")
    @ManyToOne
    @JoinColumn(name = "personnage_id")
    var personnage: Personnage? = null,

    var quantite:Int
) {


}