package com.som.som.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.som.som.common.ApiPath;
import com.som.som.dto.request.auth.SignInDto;
import com.som.som.dto.request.auth.SignUpDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.auth.SignInResponseDto;
import com.som.som.dto.response.auth.SignUpResponseDto;
import com.som.som.service.AuthService;

@RestController
@RequestMapping(ApiPath.AUTH)
public class AuthController {

    @Autowired private AuthService authService;

    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpDto requestBody) {
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }
    
    @PostMapping(SIGN_IN)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
