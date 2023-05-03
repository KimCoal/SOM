package com.som.som.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.som.som.common.ApiPath;
import com.som.som.dto.request.board.HateDto;
import com.som.som.dto.request.board.LikeDto;
import com.som.som.dto.request.board.PatchBoardDto;
import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.request.board.PostCommentDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.DeleteBoardResponseDto;
import com.som.som.dto.response.board.GetBoardResponseDto;
import com.som.som.dto.response.board.GetListResponseDto;
import com.som.som.dto.response.board.GetMyListResponseDto;
import com.som.som.dto.response.board.GetSearchListResponseDto;
import com.som.som.dto.response.board.HateResponseDto;
import com.som.som.dto.response.board.LikeResponseDto;
import com.som.som.dto.response.board.PatchBoardResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;
import com.som.som.service.BoardService;

@RestController
@RequestMapping(ApiPath.BOARD)
public class BoardController {
    
    @Autowired private BoardService boardService;

    private final String POST_BOARD = "";
    private final String POST_COMMENT = "/comment";

    private final String PATCH_BOARD = "";
    private final String LIKE = "/like";
    private final String HATE = "/hate";
    private final String DELETE_BOARD = "/{boardNumber}";

    private final String GET_BOARD = "/{boardNumber}";
    private final String GET_LIST = "/list";
    private final String GET_MY_LIST = "/my-list";
    private final String GET_SEARCH_LIST = "/search-list/{searchWord}";
    private final String GET_SEARCH_LIST_PREVIOUS = "/search-list/{searchWord}/{previousSearchWord}";

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

    @PostMapping(LIKE)
    public ResponseDto<LikeResponseDto> like(
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody LikeDto requestBody
    ) {
        ResponseDto<LikeResponseDto> response = boardService.like(email, requestBody);
        return response;
    }

    @PostMapping(HATE)
    public ResponseDto<HateResponseDto> hate(
        @AuthenticationPrincipal String email,
        @Valid @RequestBody HateDto requestBody
    ) {
        ResponseDto<HateResponseDto> response = boardService.hate(email, requestBody);
        return response;
    }

    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(
        @AuthenticationPrincipal String email,
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<DeleteBoardResponseDto> response
            = boardService.deleteBoard(email, boardNumber);
        return response;
    }

    @PatchMapping(PATCH_BOARD)
    public ResponseDto<PatchBoardResponseDto> patchBoard(
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody PatchBoardDto requestBody
    ) {
        ResponseDto<PatchBoardResponseDto> response = 
            boardService.patchBoard(email, requestBody);
        return response;
    }

    @GetMapping(GET_BOARD)
    public ResponseDto<GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }

    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyListResponseDto>> getMyList(
        @AuthenticationPrincipal String email
    ) {
        ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }

    @GetMapping(value={GET_SEARCH_LIST_PREVIOUS, GET_SEARCH_LIST})
    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(
        @PathVariable("searchWord") String searchWord,
        @PathVariable(name="previousSearchWord", required=false) String previousSearchWord
    ) {
        ResponseDto<List<GetSearchListResponseDto>> response = boardService.getSearchList(searchWord, previousSearchWord);
        return response;
    }
}
