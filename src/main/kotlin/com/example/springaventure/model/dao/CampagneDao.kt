package com.example.springaventure.model.dao;

import com.example.springaventure.model.entity.Campagne
import org.springframework.data.jpa.repository.JpaRepository

interface CampagneDao : JpaRepository<Campagne, Long> {
}