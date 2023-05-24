package com.som.som.dto.response;

import com.som.som.common.ResponseMessage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "set")
@ApiModel(value="Response Format")
public class ResponseDto<D> {

    @ApiModelProperty(value="작업 결과 상태", example="true", required=true)
    private boolean result;
    @ApiModelProperty(value="작업 결과 메세지", example=ResponseMessage.SUCCESS, required=true)
    private String message;
    @ApiModelProperty(value="작업 결과 데이터", required=true)
    private D data;

    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }
}
