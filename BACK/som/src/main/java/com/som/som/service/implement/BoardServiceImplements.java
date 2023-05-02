package com.som.som.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.som.common.ResponseMessage;
import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.request.board.PostCommentDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;
import com.som.som.entity.BoardEntity;
import com.som.som.entity.CommentEntity;
import com.som.som.entity.UserEntity;
import com.som.som.repository.BoardRepository;
import com.som.som.repository.CommentRepository;
import com.som.som.repository.HateRepository;
import com.som.som.repository.LikeRepository;
import com.som.som.repository.UserRepository;
import com.som.som.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService{
    
    @Autowired private BoardRepository boardRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;
    @Autowired private HateRepository hateRepository;

    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto) {

        PostBoardResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = new BoardEntity(userEntity, dto);
            boardRepository.save(boardEntity);

            data = new PostBoardResponseDto(boardEntity);
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<PostCommentResponseDto> postComment(String email, PostCommentDto dto) {

        PostCommentResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            CommentEntity commentEntity = new CommentEntity(userEntity, dto);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

            data = new PostCommentResponseDto(boardEntity, commentList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }
}
