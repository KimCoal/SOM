package com.som.som.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.som.som.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
    
    public BoardEntity findByBoardNumber(int boardNumber);

    public List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();
    public List<BoardEntity> findByWriterEmailOrderByBoardWriteDatetimeDesc(String writerEmail);
}
