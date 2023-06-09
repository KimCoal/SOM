package com.som.som.service;

import com.som.som.dto.request.auth.SignInDto;
import com.som.som.dto.request.auth.SignUpDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.auth.SignInResponseDto;
import com.som.som.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    public ResponseDto<SignUpResponseDto> signUp(SignUpDto dto);
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
}
