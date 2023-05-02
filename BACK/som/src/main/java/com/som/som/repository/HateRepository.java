package com.som.som.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.HateEntity;
import com.som.som.entity.PK.HatePk;

@Repository
public interface HateRepository extends JpaRepository<HateEntity, HatePk>{
    
    public List<HateEntity> findByBoardNumber(int boardNumber);
    public HateEntity findByUserEmailAndBoardNumber(String userEmail, int boardNumber);

    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}
