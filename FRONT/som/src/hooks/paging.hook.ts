import { useEffect, useState } from "react";
import { GetListResponseDto, GetMyListResponseDto, GetSearchListResponseDto } from "src/apis/response/board";
import { Comment } from "src/interfaces";

const usePagingHook = (COUNT: number) => {

  const [boardList, setBoardList] = useState<(GetListResponseDto | GetSearchListResponseDto | GetMyListResponseDto | Comment)[]>([]);
  const [viewList, setViewList] = useState<(GetListResponseDto | GetSearchListResponseDto | GetMyListResponseDto | Comment)[]>([]);
  const [pageNumber, setPageNumber] = useState<number>(1);

  const onPageHandler = (page: number) => {
    setPageNumber(page);
    
    const tmpList: (GetListResponseDto | GetSearchListResponseDto | GetMyListResponseDto | Comment)[] = [];

    const startIndex = COUNT * (page - 1);
    const endIndex = COUNT * page - 1;

    for (let index = startIndex; index <= endIndex; index++) {
      if (boardList.length < index + 1) break;
      tmpList.push(boardList[index]);
    }

    setViewList(tmpList);
  }

  useEffect(() => {
    onPageHandler(pageNumber);
  }, [boardList]);

  return {boardList, viewList, pageNumber, setBoardList, onPageHandler, COUNT};
}

export default usePagingHook;