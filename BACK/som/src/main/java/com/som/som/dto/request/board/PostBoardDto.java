package com.som.som.dto.request.board;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@ApiModel(value="게시물 작성 Request Body") 
public class PostBoardDto {
    @ApiModelProperty(value="게시물 제목", example="Board Title!!", required=true)
    @NotBlank
    private String boardTitle;

    @ApiModelProperty(value="게시물 내용", example="Board Content!!", required=true)
    @NotBlank
    private String boardContent;

    @ApiModelProperty(value="게시물 이미지 URL", example="http://~", required=false)
    private String boardImgUrl;

    @ApiModelProperty(value="게시물 카테고리", example="KR/Pop", required=true)
    private String boardCate;

}
