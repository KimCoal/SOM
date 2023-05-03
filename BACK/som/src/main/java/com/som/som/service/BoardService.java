package com.som.som.service;

import com.som.som.dto.request.board.HateDto;
import com.som.som.dto.request.board.LikeDto;
import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.request.board.PostCommentDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.DeleteBoardResponseDto;
import com.som.som.dto.response.board.HateResponseDto;
import com.som.som.dto.response.board.LikeResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;

public interface BoardService {
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto);
    public ResponseDto<PostCommentResponseDto> postComment(String email, PostCommentDto dto);
    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto);
    public ResponseDto<HateResponseDto> hate(String email, HateDto dto);
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber);
}
