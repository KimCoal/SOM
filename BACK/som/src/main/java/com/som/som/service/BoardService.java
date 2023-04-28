package com.som.som.service;

import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto);
}
