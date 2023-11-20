package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class Role constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var id: Long? = null,
    var nom:String,
    @ManyToMany(mappedBy = "roles")
     var utilisateurs: MutableList<Utilisateur> = mutableListOf()
) {



}