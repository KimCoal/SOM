package com.som.som.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.som.som.entity.PK.LikePk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Likes")
@Table(name="Likes")
@IdClass(LikePk.class)
public class LikeEntity {

    @Id
    private String userEmail;
    @Id
    private int boardNumber;
    private String userProfileUrl;
    private String userNickname;
    
    public LikeEntity(UserEntity userEntity, int boardNumber) {
        this.userEmail = userEntity.getEmail();
        this.boardNumber = boardNumber;
        this.userProfileUrl = userEntity.getProfile();
        this.userNickname = userEntity.getNickname();
    }
}
