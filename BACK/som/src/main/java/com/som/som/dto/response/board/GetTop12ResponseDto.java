package com.som.som.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.som.som.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTop12ResponseDto {
    private int boardNumber;

    private String boardTitle;

    private String boardContent;

    private String boardImgUrl;

    private String boardWriteDatetime;

    private int viewCount;

    private String writerNickname;

    private String writerProfileUrl;

    private int commentCount;

    private int likeCount;

    private int hateCount;

    public GetTop12ResponseDto(BoardEntity boardEntity) {
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.boardContent = boardEntity.getBoardContent();
        this.boardImgUrl = boardEntity.getBoardImgUrl();
        this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
        this.viewCount = boardEntity.getViewCount();
        this.writerNickname = boardEntity.getWriterNickname();
        this.writerProfileUrl = boardEntity.getWriterProfileUrl();
        this.commentCount = boardEntity.getCommentCount();
        this.likeCount = boardEntity.getLikeCount();
        this.hateCount = boardEntity.getHateCount();
    }

    public static List<GetTop12ResponseDto> copyList(List<BoardEntity> boardEntityList) {

        List<GetTop12ResponseDto> list = new ArrayList<>();

        for (BoardEntity boardEntity: boardEntityList) {
            GetTop12ResponseDto dto = new GetTop12ResponseDto(boardEntity);
            list.add(dto);
        }

        return list;

    }
}
