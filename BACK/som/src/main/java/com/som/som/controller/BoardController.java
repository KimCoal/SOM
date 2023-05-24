package com.som.som.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.som.som.dto.response.board.GetCateListResponseDto;
import com.som.som.dto.response.board.GetListResponseDto;
import com.som.som.dto.response.board.GetMyListResponseDto;
import com.som.som.dto.response.board.GetSearchListResponseDto;
import com.som.som.dto.response.board.GetTop12ResponseDto;
import com.som.som.dto.response.board.GetTop30RelatedSearchWordResponseDto;
import com.som.som.dto.response.board.GetTop30SearchWordResponseDto;
import com.som.som.dto.response.board.HateResponseDto;
import com.som.som.dto.response.board.LikeResponseDto;
import com.som.som.dto.response.board.PatchBoardResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;
import com.som.som.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.BOARD)
@RequiredArgsConstructor
@Api(description="게시물 모듈")
public class BoardController {
    
    private final BoardService boardService;

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
    private final String GET_TOP12_LIST = "/top12-list";
    private final String GET_TOP30_SEARCH_WORD = "/top30-search-word";
    private final String GET_TOP30_RELATED_SEARCH_WORD = "/top30-related-search-word/{searchWord}";
    private final String GET_CATE_LIST = "/categories/{category}";

    @ApiOperation(value="게시물 작성", notes="제목, 내용, 카테고리, 이미지를 전송하면 게시물 작성 결과로 작성된 게시물 정보를 반환, 실패시 실패 메시지를 반환")
    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostBoardDto requestBody
    ) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
        return response;
    }

    @ApiOperation(value="댓글 작성", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 boardNumber, content를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(POST_COMMENT)
    public ResponseDto<PostCommentResponseDto> postComment(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email,
        @Valid @RequestBody PostCommentDto requestBody
    ) {
        ResponseDto<PostCommentResponseDto> response = boardService.postComment(email, requestBody);
        return response;
    }

    @ApiOperation(value="좋아요 기능", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 boardNumber를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(LIKE)
    public ResponseDto<LikeResponseDto> like(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody LikeDto requestBody
    ) {
        ResponseDto<LikeResponseDto> response = boardService.like(email, requestBody);
        return response;
    }

    @ApiOperation(value="싫어요 기능", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 boardNumber를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PostMapping(HATE)
    public ResponseDto<HateResponseDto> hate(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email,
        @Valid @RequestBody HateDto requestBody
    ) {
        ResponseDto<HateResponseDto> response = boardService.hate(email, requestBody);
        return response;
    }

    @ApiOperation(value="특정 게시물 수정", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 boardNumber, title, content, boardImageUrl을 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @DeleteMapping(DELETE_BOARD)
    public ResponseDto<DeleteBoardResponseDto> deleteBoard(
        @ApiParam(hidden=true)  
        @AuthenticationPrincipal String email,
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<DeleteBoardResponseDto> response
            = boardService.deleteBoard(email, boardNumber);
        return response;
    }

    @ApiOperation(value="특정 게시물 수정", notes="Request Header Athorization에 Bearer JWT를 포함하고 Request Body에 boardNumber, title, content, boardImageUrl을 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @PatchMapping(PATCH_BOARD)
    public ResponseDto<PatchBoardResponseDto> patchBoard(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email, 
        @Valid @RequestBody PatchBoardDto requestBody
    ) {
        ResponseDto<PatchBoardResponseDto> response = 
            boardService.patchBoard(email, requestBody);
        return response;
    }

    @ApiOperation(value="특정 게시물 가져오기", notes="Path Variable에 boardNumber를 포함하여 요청을 하면, 성공시 게시물 전체 데이터를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_BOARD)
    public ResponseDto<GetBoardResponseDto> getBoard(
        @ApiParam(value="게시물 번호", example="1", required=true)
        @PathVariable("boardNumber") int boardNumber
    ) {
        ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @ApiOperation(value="전체 게시물 리스트 가져오기", notes="요청을 하면, 성공시 전체 게시물 리스트를 최신순으로 반환, 실패시 실패 메시지를 반환")
    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }

    @ApiOperation(value="본인 작성 게시물 리스트 가져오기", notes="Request Header Athorization에 Bearer JWT를 포함하여 요청을 하면, 성공시 요청자가 작성한 게시물 전체 리스트를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyListResponseDto>> getMyList(
        @ApiParam(hidden=true)
        @AuthenticationPrincipal String email
    ) {
        ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }

    @ApiOperation(value="검색어에 대한 게시물 리스트 가져오기", notes="Path Variable에 searchWord와 previousSearchWord를 포함하여 요청을 하면, 성공시 검색어에 해당하는 게시물 리스트를 최신순으로 반환, 실패시 실패 메세지를 반환")
    @GetMapping(value={GET_SEARCH_LIST_PREVIOUS, GET_SEARCH_LIST})
    public ResponseDto<List<GetSearchListResponseDto>> getSearchList(
        @ApiParam(value="검색어", example="Kpop", required=true)
        @PathVariable("searchWord") String searchWord,
        @ApiParam(value="이전 검색어", example="Pop", required=false)
        @PathVariable(name="previousSearchWord", required=false) String previousSearchWord
    ) {
        ResponseDto<List<GetSearchListResponseDto>> response = boardService.getSearchList(searchWord, previousSearchWord);
        return response;
    }

    @ApiOperation(value="좋아요 기준 상위 12개 게시물 리스트 가져오기", notes="요청을 하면, 좋아요 수 기준으로 상위 12개 게시물 리스트를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_TOP12_LIST)
    public ResponseDto<List<GetTop12ResponseDto>> getTop3List() {
        ResponseDto<List<GetTop12ResponseDto>> response = boardService.getTop12List();
        return response;
    }

    @ApiOperation(value="인기 검색어 리스트 가져오기", notes="요청을 하면, 성공시 가장 많이 검색한 30개의 검색어 리스트를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_TOP30_SEARCH_WORD)
    public ResponseDto<GetTop30SearchWordResponseDto> getTop15SearchWord() {
        ResponseDto<GetTop30SearchWordResponseDto> response = boardService.getTop30SearchWord();
        return response;
    }

    @ApiOperation(value="검색어에 해당하는 연관 검색어 리스트 가져오기", notes="Path Variable에 SearchWord를 포함하여 요청하면, 성공시 해당하는 검색어와 관련된 검색어 중 가장 많이 검색한 30개 검색어 리스트를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_TOP30_RELATED_SEARCH_WORD)
    public ResponseDto<GetTop30RelatedSearchWordResponseDto> getTop15RelatedSearchWord(
        @PathVariable("searchWord") String searchWord
    ) {
        ResponseDto<GetTop30RelatedSearchWordResponseDto> response = boardService.getTop30RelatedSearchWord(searchWord);
        return response;
    }

    @ApiOperation(value="카테고리 별 게시글 목록 가져오기", notes="요청 시 카테고리 기준으로 해당 카테고리 게시물 리스트를 반환, 실패시 실패 메세지를 반환")
    @GetMapping(GET_CATE_LIST)
    public ResponseDto<List<GetCateListResponseDto>> getCateList(
        @ApiParam(hidden=true)
        @PathVariable("category") String category
    ) {
        ResponseDto<List<GetCateListResponseDto>> response = boardService.getCateList(category);
        return response;
    }
}
