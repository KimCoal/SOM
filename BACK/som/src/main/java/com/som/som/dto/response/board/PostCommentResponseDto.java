package com.som.som.dto.response.board;

import java.util.List;

import com.som.som.entity.BoardEntity;
import com.som.som.entity.CommentEntity;
import com.som.som.entity.HateEntity;
import com.som.som.entity.LikeEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="댓글 작성 Response Body - data")
public class PostCommentResponseDto {

    @ApiModelProperty(value="게시물 Entity", required=true)
    private BoardEntity board;

    @ApiModelProperty(value="댓글 Entity list", required=true)
    private List<CommentEntity> commentList;

    @ApiModelProperty(value="싫어요 Entity list", required=true)
    private List<HateEntity> hateList;
    
    @ApiModelProperty(value="좋아요 Entity list", required=true)
    private List<LikeEntity> likeList;

}
