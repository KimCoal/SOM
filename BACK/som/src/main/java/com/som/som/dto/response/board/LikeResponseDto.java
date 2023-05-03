package com.som.som.dto.response.board;

import java.util.List;

import com.som.som.entity.BoardEntity;
import com.som.som.entity.CommentEntity;
import com.som.som.entity.HateEntity;
import com.som.som.entity.LikeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponseDto {
    private BoardEntity board;

    private List<CommentEntity> commentList;

    private List<HateEntity> hateList;
    
    private List<LikeEntity> likeList;
}