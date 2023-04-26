package com.som.som.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int commentNumber;
    private Integer parentCommentNumber;
    private String writerEmail;
    private int boardNumber;
    private String writeDatetime;
    private String commentContent;
    private String writerProfileUrl;
    private String writerNickname;
}
