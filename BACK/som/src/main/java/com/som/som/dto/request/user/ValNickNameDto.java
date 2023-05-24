package com.som.som.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="유저 닉네임 중복체크 Request Body")
public class ValNickNameDto {
    
    @ApiModelProperty(value="유저 닉네임", example="KimCoal", required=true)
    @NotBlank
    @Length(max=8)
    private String nickname;
}
