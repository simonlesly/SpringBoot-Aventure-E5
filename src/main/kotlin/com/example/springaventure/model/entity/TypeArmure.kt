package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeArmure(  @Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   @Column(name = "id", nullable = false)
                   open var id: Long? = null,
                   val nom: String,
                   val bonusType: Int) {
    @OneToMany(mappedBy = "typeArmure", cascade = [CascadeType.REMOVE])
    open var armures: MutableList<Armure> = mutableListOf()

}