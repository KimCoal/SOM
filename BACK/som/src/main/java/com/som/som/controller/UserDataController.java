package com.som.som.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.som.som.common.ApiPath;
import com.som.som.dto.request.user.PatchUserProfileDto;
import com.som.som.dto.request.user.ValEmailDto;
import com.som.som.dto.request.user.ValNickNameDto;
import com.som.som.dto.request.user.ValTelNumDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.user.GetUserResponseDto;
import com.som.som.dto.response.user.PatchUserProfileResponseDto;
import com.som.som.dto.response.user.ValEmailResponseDto;
import com.som.som.dto.response.user.ValNicknameResponseDto;
import com.som.som.dto.response.user.ValTelNumResponseDto;
import com.som.som.service.UserValService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.USER)
@RequiredArgsConstructor
@Api(description="유저 모듈")
public class UserDataController {
    
    private final UserValService userValService;

    private final String GET_USER = "/";
    private final String VALIDATE_EMAIL = "/validate/email";
    private final String VALIDATE_NICKNAME = "/validate/nickname";
    private final String VALIDATE_TEL_NUMBER = "/validate/tel-number";
    private final String PATCH_PROFILE = "/profile";

    @ApiOperation(value="유저 정보 불러오기", notes="Request Header Authorization에 Bearer Token을 포함하여 요청을 하면, 성공시 유저 정보를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email) {
        ResponseDto<GetUserResponseDto> response = userValService.getUser(email);
        return response;
    }

    @ApiOperation(value="유저 이메일 중복체크", notes="Request Body에 email을 포함하여 요청하면, 중복 결과를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(VALIDATE_EMAIL)
    public ResponseDto<ValEmailResponseDto> validateEmail(
        @Valid @RequestBody ValEmailDto requsetBody
    ) {
        ResponseDto<ValEmailResponseDto> response = userValService.validateEmail(requsetBody);
        return response;
    }

    @ApiOperation(value="유저 닉네임 중복체크", notes="Request Body에 nickname을 포함하여 요청하면, 중복 결과를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(VALIDATE_NICKNAME)
    public ResponseDto<ValNicknameResponseDto> validateNickname(
        @Valid @RequestBody ValNickNameDto requsetBody
    ) {
        ResponseDto<ValNicknameResponseDto> response = userValService.validateNickname(requsetBody);
        return response;
    }

    @ApiOperation(value="유저 휴대전화번호 중복체크", notes="Request Body에 telNumber를 포함하여 요청하면, 중복 결과를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(VALIDATE_TEL_NUMBER)
    public ResponseDto<ValTelNumResponseDto> validateTelNumber(
        @Valid @RequestBody ValTelNumDto requsetBody
    ) {
        ResponseDto<ValTelNumResponseDto> response = userValService.validateTelNumber(requsetBody);
        return response;
    }

    @ApiOperation(value="유저 프로필 URL 수정", notes="Request Header Authorization에 Bearer JWT를 포함하고 Request Body에 profile을 포함하여 요청을 하면, 성공시 유저 정보를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchUserProfileResponseDto> patchProfile(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PatchUserProfileDto requestBody
    ) {
        ResponseDto<PatchUserProfileResponseDto> response = userValService.patchProfile(email, requestBody);
        return response;
    }
}
