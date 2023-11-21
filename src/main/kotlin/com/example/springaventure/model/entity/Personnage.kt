package com.example.springaventure.model.entity

import jakarta.persistence.*

@Entity
open class Personnage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    val nom: String,

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
    @OneToMany(mappedBy = "monstre", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    open var combats: MutableList<Combat> = mutableListOf(),
    @OneToMany(mappedBy = "personnage", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    open var ligneInventaires: MutableList<LigneInventaire> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    open var utilisateur: Utilisateur? = null
) {

    //TODO bonus accesoire
    val pointDeVieMax: Int
        get() = 50 + (10 * (this.endurance))
    var pointDeVie: Int = this.pointDeVieMax
        set(value) {
            field = minOf(value, this.pointDeVieMax)
        }

    @OneToMany(mappedBy = "hero", orphanRemoval = true)
    open var campagnes: MutableList<Campagne> = mutableListOf()
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
    open fun equipe(arme: Arme): String {
        val ligneArme = this.ligneInventaires.find({ ligneInventaire -> ligneInventaire.item == arme })
        if (ligneArme != null) {
            armeEquipee = arme
            return "${this.nom} équipe ${this.armeEquipee!!.nom}."
        } else {
            return "$nom n'a pas cette arme dans son inventaire."
        }
    }

    fun equipe(armure: Armure): String {
        val ligneArmure = this.ligneInventaires.find({ ligneInventaire -> ligneInventaire.item == armure })
        if (ligneArmure != null) {
            this.armureEquipee = armure
            return "${this.nom} équipe ${this.armeEquipee!!.nom}."
        } else {
            return "$nom n'a pas cette armure dans son inventaire."
        }
    }

    // Méthode pour boire une potion de l'inventaire
    fun boirePotion(): String {
        //TODO refaire la logique de boire potion
        val lignePotions: List<LigneInventaire> =
            this.ligneInventaires.filter { ligneInventaire -> ligneInventaire.item is Potion }
        if (lignePotions.size > 0) {
            return lignePotions[0].item!!.utiliser(this)
        } else {
            return "$nom n'a pas de potion dans son inventaire."
        }

    }

    /**
     * Vérification si le personnage a une potion dans son inventaire
     * @return true si il a une potion false sinon
     */
    fun aUnePotion(): Boolean {
        return this.ligneInventaires.filter { ligneInventaire -> ligneInventaire.item is Potion }.size > 0
    }

    /**
     * Loot l'inventaire de la cible
     */
    fun loot(cible: Personnage): String {
        cible.armeEquipee = null
        cible.armureEquipee = null
        var msg = ""
        for (uneLigne: LigneInventaire in cible.ligneInventaires) {
            this.ajouterLigneInventaire(uneLigne.item!!, uneLigne.quantite)
            msg += "${this.nom} récupère ${uneLigne.quantite} ${uneLigne.item}\n"
        }
        return msg
    }

    fun ajouterLigneInventaire(unItem: Item, uneQuantite: Int) {
        val ligneItem = this.ligneInventaires.find { ligneInventaire -> ligneInventaire.item == unItem }
        if (ligneItem == null) {
            val ligneInventaireId = LigneInventaireId(this.id!!, unItem.id!!)
            this.ligneInventaires.add(LigneInventaire(ligneInventaireId, unItem, this, uneQuantite))
        } else {
            ligneItem.quantite += uneQuantite
        }
    }


    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}