package com.som.som.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.som.som.entity.resultSet.RelatedSearchWordResultSet;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiOperation(value="검색어에 해당하는 연관 검색어 리스트 가져오기 Response Body - data")
public class GetTop30RelatedSearchWordResponseDto {
    
    @ApiModelProperty(value="상위 30개의 연관 검색어 리스트", example="['Kpop', 'pop', 'Jpop']", required=true)
    private List<String> top30SearchWordList;

    public static GetTop30RelatedSearchWordResponseDto copyList(List<RelatedSearchWordResultSet> list) {
        List<String> result = new ArrayList<>();
        for (RelatedSearchWordResultSet item: list) {
            result.add(item.getPreviousSearchWord());
        }
        return new GetTop30RelatedSearchWordResponseDto(result);
    }
}
