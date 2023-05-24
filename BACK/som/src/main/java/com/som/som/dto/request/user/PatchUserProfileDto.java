package com.som.som.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value="유저 프로필 URL 수정 Request Body")
public class PatchUserProfileDto {
    
    @ApiModelProperty(value="프로필 사진 URL", example="http://~", required=true)
    @NotBlank
    @URL
    private String profile;
}
