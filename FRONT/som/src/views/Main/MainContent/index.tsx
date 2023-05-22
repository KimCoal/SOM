import { useEffect, useState } from 'react';

import axios, { AxiosResponse } from 'axios';
import { Box, Grid, Pagination, Typography, Stack, Card } from '@mui/material'

import BoardList from 'src/components/BoardList'
import PopularMain from 'src/components/PopularMain'
import { getPageCount } from 'src/utils';
import { usePagingHook } from 'src/hooks';
import ResponseDto from 'src/apis/response';
import { GetListResponseDto, GetTop30SearchWordResponseDto } from 'src/apis/response/board';
import { GET_LIST_URL, GET_TOP30_SEARCH_WORD_URL } from 'src/constants/api';

export default function MainContents() {
  
  const { viewList, pageNumber, boardList, setBoardList, onPageHandler, COUNT } = usePagingHook(5);
  
  const [popularList, setPopularList] = useState<string[]>([]);

  const getList = () => {
    axios.get(GET_LIST_URL)
      .then((response) => getListResponseHandler(response))
      .catch((error) => getListErrorHandler(error));
  }

  const getTop30SearchWord = () => {
    axios.get(GET_TOP30_SEARCH_WORD_URL)
      .then((response) => getTop30SearchWordResponseHandler(response))
      .catch((error) => getTop30SearchWordErrorHandler(error));
  }

  const getListResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<GetListResponseDto[]>;
    if (!result || data === null) return;
    setBoardList(data);
  }

  const getTop30SearchWordResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<GetTop30SearchWordResponseDto>
    if (!result || !data) return;
    setPopularList(data.top30SearchWordList);
  }

  const getListErrorHandler = (error: any) => {
    console.log(error.message);
  }

  const getTop30SearchWordErrorHandler = (error: any) => {
    console.log(error.message);
  }

  useEffect(() => {
    getList();
    getTop30SearchWord();
  }, [])

  return (
    <Box sx={{
              p: '40px 120px',
              backgroundColor: 'rgba(0, 0, 0, 0.05)',
              backgroundRepeat : "no-repeat",
              backgroundSize: 'inherit',
              }}>
      <Box>
        <Typography sx={{ fontSize: '24px', fontWeight: 500 }}>최신 게시물</Typography>
      </Box>
      <Box sx={{ pt: '20px', pb: '80px' }}>
        <Grid container spacing={3}>
          <Grid item sm={12} md={9}>
            <Stack spacing={2}>
              {viewList.map((boardItem) => (<BoardList item={boardItem as GetListResponseDto} />))}
            </Stack>
          </Grid>
          <Grid item sm={12} md={3}>
            <PopularMain title="인기 검색어" popularList={popularList} />
          </Grid>
        </Grid>
      </Box>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>
        <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
      </Box>
    </Box>
  )
}