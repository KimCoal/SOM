package com.som.som.service;

import com.som.som.dto.request.user.ValEmailDto;
import com.som.som.dto.request.user.ValNickNameDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.user.GetUserResponseDto;
import com.som.som.dto.response.user.ValEmailResponseDto;
import com.som.som.dto.response.user.ValNicknameResponseDto;

public interface UserValService {
    public ResponseDto<GetUserResponseDto> getUser(String email);
    public ResponseDto<ValEmailResponseDto> validateEmail(ValEmailDto dto);
    public ResponseDto<ValNicknameResponseDto> validateNickname(ValNickNameDto dto);
}
