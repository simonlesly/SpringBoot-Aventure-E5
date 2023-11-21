package com.example.springaventure.model.entity

import jakarta.persistence.Embeddable

@Embeddable
class LigneInventaireId (
    var personnageId:Long,
    var itemId:Long,
        ):java.io.Serializable {

}