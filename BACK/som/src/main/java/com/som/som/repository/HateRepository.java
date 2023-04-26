package com.som.som.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.HateEntity;
import com.som.som.entity.PK.HatePk;

@Repository
public interface HateRepository extends JpaRepository<HateEntity, HatePk>{
    
}
