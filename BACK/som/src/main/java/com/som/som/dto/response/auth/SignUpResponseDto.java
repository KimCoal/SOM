package com.som.som.dto.response.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="회원가입 Response Body - data")
public class SignUpResponseDto {
    @ApiModelProperty(value="회원가입 결과", example="true", required=true)
    private boolean status;
}
