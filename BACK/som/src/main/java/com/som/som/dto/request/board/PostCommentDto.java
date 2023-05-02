package com.som.som.dto.request.board;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCommentDto {
    @Min(1)
    private int boardNumber;

    @NotBlank
    private String commentContent;
}
