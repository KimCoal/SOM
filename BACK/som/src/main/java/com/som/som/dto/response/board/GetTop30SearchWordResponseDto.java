package com.som.som.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.som.som.entity.resultSet.SearchWordResultSet;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="인기 검색어 리스트 가져오기 Response Body - data")
public class GetTop30SearchWordResponseDto {
    
    @ApiModelProperty(value="상위 30개의 검색어 리스트", example="['Kpop', 'pop', 'Jpop']", required=true)
    private List<String> top30SearchWordList;

    public static GetTop30SearchWordResponseDto copyList(List<SearchWordResultSet> list) {
        List<String> result = new ArrayList<>();
        for (SearchWordResultSet item: list) {
            result.add(item.getSearchWord());
        }
        return new GetTop30SearchWordResponseDto(result);
    }
}
