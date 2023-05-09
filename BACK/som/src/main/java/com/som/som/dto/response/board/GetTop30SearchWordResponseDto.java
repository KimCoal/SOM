package com.som.som.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.som.som.entity.resultSet.SearchWordResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTop30SearchWordResponseDto {

    private List<String> top15SearchWordList;

    public static GetTop30SearchWordResponseDto copyList(List<SearchWordResultSet> list) {
        List<String> result = new ArrayList<>();
        for (SearchWordResultSet item: list) {
            result.add(item.getSearchWord());
        }
        return new GetTop30SearchWordResponseDto(result);
    }
}
