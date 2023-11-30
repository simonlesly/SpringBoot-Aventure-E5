package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeArme constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,
    var nom: String,
    var nbrDes: Int,
    var maxDes: Int,
    var mutiplicateurCritique: Int,
    var limiteCritique: Int,
    @OneToMany(mappedBy = "typeArme", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    var armes: MutableList<Arme> = mutableListOf()
) {


}