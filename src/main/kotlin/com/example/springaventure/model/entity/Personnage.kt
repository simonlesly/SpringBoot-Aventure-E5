package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
open class Personnage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom: String,
    var pointDeVie: Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    @ManyToOne
    @JoinColumn(name = "arme_equipee_id")
    var armeEquipee: Arme? = null,
    @ManyToOne
    @JoinColumn(name = "armure_equipee_id")
    var armureEquipee: Armure? = null,

    val inventaire: MutableList<Item> = mutableListOf()
) {

    fun calculeDefense(): Int {
        var resultat = this.defense / 2
        val scoreArmure =
            (this.armureEquipee?.typeArmure?.bonusType ?: 0) + (this.armureEquipee?.qualite?.bonusQualite ?: 0)
        resultat += scoreArmure
        return resultat;

    }

    // Méthode pour attaquer un adversaire
    open fun attaquer(adversaire: Personnage) {
        // Vérifier si le personnage a une arme équipée
        var degats = this.attaque / 2
        if (armeEquipee != null) {
            // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'arme
            degats += this.armeEquipee!!.calculerDegats()
        }
        // Appliquer la défense de l'adversaire (au minimum au moins 1 de dégat)
        val degatsInfliges = maxOf(1, degats - adversaire.calculeDefense())


        // Appliquer les dégâts à l'adversaire
        adversaire.pointDeVie = adversaire.pointDeVie - degatsInfliges

        println("$nom attaque ${adversaire.nom} avec ${armeEquipee?.nom ?: "une attaque de base"} et inflige $degatsInfliges points de dégâts.")
    }

    // Méthode pour équiper une arme de l'inventaire
    open fun equipe(arme: Arme) {
        if (inventaire.contains(arme)) {
            armeEquipee = arme
            println("${this.nom} équipe ${this.armeEquipee!!.nom}.")
        } else {
            println("$nom n'a pas cette arme dans son inventaire.")
        }
    }

    fun equipe(armure: Armure) {
        if (inventaire.contains(armure)) {
            this.armureEquipee = armure
        } else {
            println("$nom n'a pas cette armure dans son inventaire.")
        }
    }

    // Méthode pour boire une potion de l'inventaire
    fun boirePotion():String {
        //TODO refaire la logique de boire potion
        val potions = inventaire.filterIsInstance<Potion>()
        if (potions.isNotEmpty()) {
            val potion = potions.first()
            val soin = potion.soin
            pointDeVie += soin
            if (pointDeVie > pointDeVieMax) {
                pointDeVie = pointDeVieMax
            }
            inventaire.remove(potion)
            return ("$nom boit ${potion.nom} et récupère $soin points de vie.")
        } else {
            return ("$nom n'a pas de potion dans son inventaire.")
        }
    }

    /**
     * Vérification si le personnage a une potion dans son inventaire
     * @return true si il a une potion false sinon
     */
    fun aUnePotion(): Boolean {
        return inventaire.any { it is Potion }
    }

    /**
     * Loot l'inventaire de la cible
     */
    fun loot(cible: Personnage) :String{
        cible.armeEquipee = null
        cible.armureEquipee = null
        this.inventaire.addAll(cible.inventaire)
        var msg=""
        cible.inventaire.forEach({ msg+=("${this.nom} récupère un/une $it\n") })

        cible.inventaire.clear()
        return msg
    }

    /**
     * Affiche les items de l'inventaire avec index et descriptions
     */
    fun afficherInventaire() {
        println("Inventaire de $nom:")
        if (inventaire.isEmpty()) {
            println("L'inventaire est vide.")
        } else {
            for ((index, item) in inventaire.withIndex()) {
                println("$index => ${item} ")
            }
        }
    }


    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }



}