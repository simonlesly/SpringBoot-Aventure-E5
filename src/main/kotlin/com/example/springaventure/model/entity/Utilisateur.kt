package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
class Utilisateur constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
     var id: Long? = null,
    @Column(unique = true)
    var email:String,
    var mdp:String,

    @ManyToMany
    @JoinTable(
        name = "Utilisateur_roles",
        joinColumns = [JoinColumn(name = "utilisateur_id")],
        inverseJoinColumns = [JoinColumn(name = "roles_id")]
    )
     var roles: MutableList<Role> = mutableListOf()

) {
    @OneToMany(mappedBy = "utilisateur", cascade = [CascadeType.REMOVE])
    open var campagnes: MutableList<Campagne> = mutableListOf()


    @OneToMany(mappedBy = "utilisateur", orphanRemoval = true)
    open var personnages: MutableList<Personnage> = mutableListOf()
}