package com.som.som.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchUserProfileDto {
    @NotBlank
    @URL
    private String profile;
}
