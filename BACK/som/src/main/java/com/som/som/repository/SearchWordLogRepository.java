package com.som.som.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.som.som.entity.SearchWordLogEntity;
import com.som.som.entity.resultSet.SearchWordResultSet;

@Repository
public interface SearchWordLogRepository extends JpaRepository<SearchWordLogEntity, Integer> {

    @Query(value=
                "SELECT search_word AS searchWord, count(search_word) AS count " + 
                "FROM Searchwordlog " + 
                "GROUP BY search_word " + 
                "ORDER BY count DESC " + 
                "LIMIT 30", nativeQuery=true)
    public List<SearchWordResultSet> findTop30();
}
