package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class Qualite constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom:String,
    var couleur:String,
    var bonusQualite:Int
    //TODO Mission 3 Ajouter les associations
) {


}