package com.som.som.service;

import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.user.GetUserResponseDto;

public interface UserValService {
    public ResponseDto<GetUserResponseDto> getUser(String email);
}
