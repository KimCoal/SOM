package com.som.som.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(ApiPath.USER)
public class UserDataController {
    
    @Autowired private UserValService userValService;

    private final String GET_USER = "/";
    private final String VALIDATE_EMAIL = "/validate/email";
    private final String VALIDATE_NICKNAME = "/validate/nickname";
    private final String VALIDATE_TEL_NUMBER = "/validate/tel-number";
    private final String PATCH_PROFILE = "/profile";

    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email) {
        ResponseDto<GetUserResponseDto> response = userValService.getUser(email);
        return response;
    }

    @PostMapping(VALIDATE_EMAIL)
    public ResponseDto<ValEmailResponseDto> validateEmail(
        @Valid @RequestBody ValEmailDto requsetBody
    ) {
        ResponseDto<ValEmailResponseDto> response = userValService.validateEmail(requsetBody);
        return response;
    }

    @PostMapping(VALIDATE_NICKNAME)
    public ResponseDto<ValNicknameResponseDto> validateNickname(
        @Valid @RequestBody ValNickNameDto requsetBody
    ) {
        ResponseDto<ValNicknameResponseDto> response = userValService.validateNickname(requsetBody);
        return response;
    }
    
    @PostMapping(VALIDATE_TEL_NUMBER)
    public ResponseDto<ValTelNumResponseDto> validateTelNumber(
        @Valid @RequestBody ValTelNumDto requsetBody
    ) {
        ResponseDto<ValTelNumResponseDto> response = userValService.validateTelNumber(requsetBody);
        return response;
    }

    @PatchMapping(PATCH_PROFILE)
    public ResponseDto<PatchUserProfileResponseDto> patchProfile(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PatchUserProfileDto requestBody
    ) {
        ResponseDto<PatchUserProfileResponseDto> response = userValService.patchProfile(email, requestBody);
        return response;
    }
}
