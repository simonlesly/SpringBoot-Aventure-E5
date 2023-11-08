package com.example.springaventure.models.entities

import jakarta.persistence.*

@Entity
class Qualite constructor() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

     var nom:String

}