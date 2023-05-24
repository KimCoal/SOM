package com.som.som.dto.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="휴대전화번호 중복체크 Response Body - data")
public class ValTelNumResponseDto {
    @ApiModelProperty(value="중복체크 결과", example="true", required=true)
    private boolean result;
}
