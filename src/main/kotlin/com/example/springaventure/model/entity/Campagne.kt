package com.example.springaventure.model.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Campagne constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom:String,
    var dateMaj:LocalDate,
    var dernierScore:Int,
    var meilleurScore:Int,
    var statut:String,
    @OneToMany(mappedBy = "campagne", cascade = [CascadeType.REMOVE])
    var combats: MutableList<Combat> = mutableListOf(),
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
     var utilisateur: Utilisateur? = null,
    @ManyToOne
    @JoinColumn(name = "hero_id")
    var hero: Personnage? = null

) {



}