package com.som.som.dto.request.board;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HateDto {
    @Min(1)
    private int boardNumber;
}
