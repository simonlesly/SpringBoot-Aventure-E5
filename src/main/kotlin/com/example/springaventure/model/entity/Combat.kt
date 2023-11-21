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
    open var campagne: Campagne? = null
) {



}