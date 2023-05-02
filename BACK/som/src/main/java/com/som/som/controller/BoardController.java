package com.som.som.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.som.som.common.ApiPath;
import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.request.board.PostCommentDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;
import com.som.som.service.BoardService;

@RestController
@RequestMapping(ApiPath.BOARD)
public class BoardController {
    
    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";
    private final String POST_COMMENT = "/comment";

    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(

        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
        return response;
    }

    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostCommentDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(email, requestBody);
        return response;
    }
}
