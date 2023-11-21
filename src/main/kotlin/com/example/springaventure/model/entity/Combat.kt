package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class Combat constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nbTour:Int,
    var nbTourMin:Int,
    @ManyToOne
    @JoinColumn(name = "campagne_id")
    open var campagne: Campagne? = null,
    @ManyToOne
    @JoinColumn(name = "monstre_id", nullable = false)
    open var monstre: Personnage? = null

) {
    fun tourDeMonstre() {
        println("---Tour de ${monstre.nom} (points de vie: ${monstre.pointDeVie}---)\u001B[31m")
        val nbAlea = (0..100).random()
        // Le monstre a une faible chance (par exemple, 10%) de boire une potion s'il est blessé
        if (monstre.pointDeVie < monstre.pointDeVieMax / 2 && nbAlea < 10 && monstre.aUnePotion()) {
            monstre.boirePotion()
        } else {
            if (nbAlea < 60) {
                monstre.attaquer(joueur)
            } else {
                println("${monstre.nom} choisit de passer.")
            }
        }
        println("\u001b[0m")
    }



}