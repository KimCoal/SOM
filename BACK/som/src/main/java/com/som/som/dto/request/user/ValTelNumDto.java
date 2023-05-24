package com.som.som.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="유저 휴대전화번호 중복체크 Request Body")
public class ValTelNumDto {
    @ApiModelProperty(value="유저 휴대전화번호", example="010-1234-4741", required=true)
    @NotBlank
    @Length(max=15)
    private String telNumber;
}
