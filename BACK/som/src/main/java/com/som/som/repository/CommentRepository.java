package com.som.som.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    
    public List<CommentEntity> findByBoardNumberOrderByWriteDatetimeDesc(int boardNumber);
    
    @Transactional
    public void deleteByBoardNumber(int boardNumber);
}
