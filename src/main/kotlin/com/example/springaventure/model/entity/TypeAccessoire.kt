package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeAccessoire (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom:String,
    var typeBonus:String,
) {
    @OneToMany(mappedBy = "typeAccessoire", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    open var accessoires: MutableList<Accessoire> = mutableListOf()

}