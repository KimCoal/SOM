package com.som.som.entity;

import javax.persistence.Id;
import javax.persistence.IdClass;

import com.som.som.entity.PK.HatePk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HatePk.class)
public class HateEntity {
    
    @Id
    private String userEmail;
    @Id
    private int boardNumber;
    private String userProfileUrl;
    private String userNickname;
}
