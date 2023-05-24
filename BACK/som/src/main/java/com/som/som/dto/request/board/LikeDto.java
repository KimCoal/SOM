package com.som.som.dto.request.board;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="좋아요 기능 Request Body")
public class LikeDto {

    @ApiModelProperty(value="게시물 번호", example="1", required=true)
    @Min(1)
    private int boardNumber;
}
