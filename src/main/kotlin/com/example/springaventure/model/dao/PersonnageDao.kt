package com.example.springaventure.model.dao;

import com.example.springaventure.model.entity.Personnage
import org.springframework.data.jpa.repository.JpaRepository

interface PersonnageDao : JpaRepository<Personnage, Long> {
}