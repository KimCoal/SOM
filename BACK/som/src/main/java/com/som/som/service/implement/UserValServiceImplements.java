package com.som.som.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.som.common.ResponseMessage;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.user.GetUserResponseDto;
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
}
