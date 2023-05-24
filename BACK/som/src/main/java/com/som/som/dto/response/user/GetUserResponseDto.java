package com.som.som.dto.response.user;

import com.som.som.entity.UserEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="유저 정보 불러오기 Response Body - data")
public class GetUserResponseDto {
    @ApiModelProperty(value="사용자 이메일", example="johnlove2572@gmail.com", required=true)
    private String email;

    @ApiModelProperty(value="사용자 닉네임", example="KimCoal", required=true)
    private String nickname;

    @ApiModelProperty(value="사용자 휴대전화번호", example="010-1234-4741", required=true)
    private String telNumber;

    @ApiModelProperty(value="사용자 주소", example="부산광역시 연제구", required=true)
    private String address;

    @ApiModelProperty(value="사용자 프로필 URL", example="http://", required=true)
    private String profile;

    public GetUserResponseDto(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.telNumber = userEntity.getTelNumber();
        this.address = userEntity.getAddress();
        this.profile = userEntity.getProfile();
    }
}
