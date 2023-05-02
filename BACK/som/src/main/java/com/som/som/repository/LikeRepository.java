package com.som.som.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.LikeEntity;
import com.som.som.entity.PK.LikePk;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, LikePk>{
    
    public List<LikeEntity> findByBoardNumber(int boardNumber);
    public LikeEntity findByUserEmailAndBoardNumber(String userEmail, int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}
