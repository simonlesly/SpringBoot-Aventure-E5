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
    @OneToMany(mappedBy = "campagne", cascade = [CascadeType.REMOVE])
    var combats: MutableList<Combat> = mutableListOf()
) {



}