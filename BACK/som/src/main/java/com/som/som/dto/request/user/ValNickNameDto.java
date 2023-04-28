package com.som.som.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValNickNameDto {
    @NotBlank
    @Length(max=8)
    private String nickname;
}
