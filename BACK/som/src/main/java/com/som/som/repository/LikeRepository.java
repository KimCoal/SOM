package com.som.som.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.LikeEntity;
import com.som.som.entity.PK.LikePk;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikePk>{
    
}
