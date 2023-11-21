package com.example.springaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

// Déclare la classe comme une entité JPA.
@Entity
// Spécifie la valeur du discriminateur associée à cette sous-classe.
@DiscriminatorValue("potion")
class Potion(
    // Déclaration des propriétés de la classe Potion, héritées de la classe Item
    // L'identifiant unique de la potion
    id: Long? = null,

    // Le nom de la potion
    nom: String,

    // La description de la potion
    description: String,
    //Chemin ver l'image
    cheminImage:String?,

    // La propriété spécifique à la potion, le soin qu'elle procure
    val soin: Int

) : Item(id, nom, description,cheminImage) {

    /**
     * Utilise la potion pour soigner un personnage en appelant la méthode boirePotion sur la cible.
     *
     * @param cible Le personnage sur lequel la potion est utilisée pour soigner.
     */
    override fun utiliser(cible: Personnage) {
        //TODO Refaire la logique de boire potion
        cible.boirePotion()
    }
}