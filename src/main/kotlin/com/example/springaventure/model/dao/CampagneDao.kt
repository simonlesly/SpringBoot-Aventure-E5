package com.example.springaventure.model.dao;

import com.example.springaventure.model.entity.Campagne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CampagneDao : JpaRepository<Campagne, Long> {


    @Query("select c from Campagne c where upper(c.utilisateur.email) = upper(?1)")
    fun findByUtilisateur_EmailIgnoreCase(email: String): List<Campagne>

}