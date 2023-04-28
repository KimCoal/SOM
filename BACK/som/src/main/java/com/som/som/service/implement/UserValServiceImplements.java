package com.som.som.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.som.common.ResponseMessage;
import com.som.som.dto.request.user.ValEmailDto;
import com.som.som.dto.request.user.ValNickNameDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.user.GetUserResponseDto;
import com.som.som.dto.response.user.ValEmailResponseDto;
import com.som.som.dto.response.user.ValNicknameResponseDto;
import com.som.som.entity.UserEntity;
import com.som.som.repository.UserRepository;
import com.som.som.service.UserValService;

@Service
public class UserValServiceImplements implements UserValService {
    
    @Autowired private UserRepository userRepository;

    public ResponseDto<GetUserResponseDto> getUser(String email) {
        
        GetUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            
            data = new GetUserResponseDto(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ValEmailResponseDto> validateEmail(ValEmailDto dto) {
        ValEmailResponseDto data = null;

        String email = dto.getEmail();

        try {
            boolean hasEmail = userRepository.existsByEmail(email);
            data = new ValEmailResponseDto(!hasEmail);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<ValNicknameResponseDto> validateNickname(ValNickNameDto dto) {
        ValNicknameResponseDto data = null;

        String nickname = dto.getNickname();

        try {
            boolean hasNickname = userRepository.existsByNickname(nickname);
            data = new ValNicknameResponseDto(!hasNickname);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
