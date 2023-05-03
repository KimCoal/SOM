package com.som.som.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.som.som.common.ResponseMessage;
import com.som.som.dto.request.board.HateDto;
import com.som.som.dto.request.board.LikeDto;
import com.som.som.dto.request.board.PatchBoardDto;
import com.som.som.dto.request.board.PostBoardDto;
import com.som.som.dto.request.board.PostCommentDto;
import com.som.som.dto.response.ResponseDto;
import com.som.som.dto.response.board.DeleteBoardResponseDto;
import com.som.som.dto.response.board.GetBoardResponseDto;
import com.som.som.dto.response.board.HateResponseDto;
import com.som.som.dto.response.board.LikeResponseDto;
import com.som.som.dto.response.board.PatchBoardResponseDto;
import com.som.som.dto.response.board.PostBoardResponseDto;
import com.som.som.dto.response.board.PostCommentResponseDto;
import com.som.som.entity.BoardEntity;
import com.som.som.entity.CommentEntity;
import com.som.som.entity.HateEntity;
import com.som.som.entity.LikeEntity;
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

    public ResponseDto<LikeResponseDto> like(String email, LikeDto dto) {

        LikeResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            HateEntity hateEntity = hateRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (hateEntity != null) return ResponseDto.setFailed(ResponseMessage.LIKE_ERROR);

            LikeEntity likeEntity = likeRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (likeEntity == null) {
                likeEntity = new LikeEntity(userEntity, boardNumber);
                likeRepository.save(likeEntity);
                boardEntity.increaseLikeCount();
            }
            else {
                likeRepository.delete(likeEntity);
                boardEntity.decreaseLikeCount();
            }
            boardRepository.save(boardEntity);

            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<LikeEntity> likeList = likeRepository.findByBoardNumber(boardNumber);
            List<HateEntity> hateList = hateRepository.findByBoardNumber(boardNumber);

            data = new LikeResponseDto(boardEntity, commentList, hateList, likeList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<HateResponseDto> hate(String email, HateDto dto) {

        HateResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            LikeEntity likeEntity = likeRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (likeEntity != null) return ResponseDto.setFailed(ResponseMessage.HATE_ERROR);

            HateEntity hateEntity = hateRepository.findByUserEmailAndBoardNumber(email, boardNumber);
            if (hateEntity == null) {
                hateEntity = new HateEntity(userEntity, boardNumber);
                hateRepository.save(hateEntity);
                boardEntity.increaseHateCount();
            } 
            else {
                hateRepository.delete(hateEntity);
                boardEntity.decreaseHateCount();
            }
            if (boardEntity.getHateCount() >= 20){
                deleteBoard(email, boardNumber);
                return  ResponseDto.setFailed(ResponseMessage.AUTO_DEL);
            } 
            else boardRepository.save(boardEntity);
            
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<HateEntity> hateList = hateRepository.findByBoardNumber(boardNumber);
            List<LikeEntity> likeList = likeRepository.findByBoardNumber(boardNumber);
            
            data = new HateResponseDto(boardEntity, commentList, hateList, likeList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<DeleteBoardResponseDto> deleteBoard(String email, int boardNumber) {

        DeleteBoardResponseDto data = null;

        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if (!isEqualWriter) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            commentRepository.deleteByBoardNumber(boardNumber);
            likeRepository.deleteByBoardNumber(boardNumber);
            hateRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);
            data = new DeleteBoardResponseDto(true);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto dto) {

        PatchBoardResponseDto data = null;

        int boardNumber = dto.getBoardNumber();

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

            boolean isEqualWriter = email.equals(boardEntity.getWriterEmail());
            if (!isEqualWriter) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            List<LikeEntity> likyList = likeRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            List<HateEntity> hateList = hateRepository.findByBoardNumber(boardNumber);

            data = new PatchBoardResponseDto(boardEntity, commentList, likyList, hateList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }

    public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {

        GetBoardResponseDto data = null;

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);
            List<LikeEntity> likyList = likeRepository.findByBoardNumber(boardNumber);
            List<HateEntity> hateList = hateRepository.findByBoardNumber(boardNumber);
            List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);
            
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

            data = new GetBoardResponseDto(boardEntity, commentList, likyList, hateList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

    }
}
