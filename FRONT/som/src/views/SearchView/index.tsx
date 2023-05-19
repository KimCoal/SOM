import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

import axios, { AxiosResponse } from 'axios';
import { Box, Grid, Pagination, Stack, Typography } from '@mui/material'

import PopularMain from 'src/components/PopularMain'
import BoardList from 'src/components/BoardList';
import { getPageCount } from 'src/utils';
import { usePagingHook } from 'src/hooks';
import ResponseDto from 'src/apis/response';
import { GetSearchListResponseDto, GetTop30RelatedSearchWordResponseDto } from 'src/apis/response/board';
import { GET_SEARCH_LIST_URL, GET_TOP30_RELATED_SEARCH_WORD_URL } from 'src/constants/api';

export default function SearchView() {

    const { content } = useParams();
    const { viewList, pageNumber, boardList, setBoardList, onPageHandler, COUNT } = usePagingHook(5);
    
    const [popularList, setPopularList] = useState<string[]>([]);
    const [previousSearchWord, setPreviousSearchWord] = useState<string>('');

    let loadFlag = true;

    const getSearchList = () => {
        axios.get(GET_SEARCH_LIST_URL(content as string, previousSearchWord))
            .then((response) => getSearchListResponseHandler(response))
            .catch((error) => getSearchListErrorHandler(error));
    }

    const getTop30RelatedSearchWord = () => {
        axios.get(GET_TOP30_RELATED_SEARCH_WORD_URL(content as string))
            .then((response) => getTop30RelatedSearchWordResponseHandler(response))
            .catch((error) => getTop30RelatedSearchWordErrorHandler(error));
    }

    const getSearchListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetSearchListResponseDto[]>;
        if (!result || data == null) return;
        setBoardList(data);
    }

    const getTop30RelatedSearchWordResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetTop30RelatedSearchWordResponseDto>;
        if (!result || !data) return;
        setPopularList(data.top30SearchWordList);
    }

    const getSearchListErrorHandler = (error: any) => {
        console.log(error.message);
    }

    const getTop30RelatedSearchWordErrorHandler = (error: any) => {
        console.log(error.message);
    }

    useEffect(() => {
        if (loadFlag) {
            loadFlag = false;
            getSearchList();
            getTop30RelatedSearchWord();
            setPreviousSearchWord(content as string);
        }
    }, [content]);

  return (
    <Box sx={{ p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
        <Box sx={{ fontSize: '24px', fontWeight: 500 }}>
            <Box component='strong'>{content}</Box>
            <Typography component='span' sx={{ fontSize: '24px', fontWeight: 500, opacity: 0.7 }}>에 대한 검색결과 입니다. </Typography>
            <Box component='strong'>{boardList.length}</Box>
        </Box>
        <Box sx={{ pt: '20px', pb: '80px' }}>
            <Grid container spacing={3}>
                <Grid item sm={12} md={8}>
                    <Stack spacing={2}>
                        {viewList.length === 0 ? (<Box sx={{ height: '416px', display: 'flex', justifyContent: 'center', alignItems: 'center' }}><Typography sx={{ fontSize: '24px', fontWeight: 500, color: 'rgba(0, 0, 0, 0.4)' }}>검색결과가 없습니다.</Typography></Box>) : viewList.map((boardItem) => (<BoardList item={boardItem as GetSearchListResponseDto} />))}
                    </Stack>
                </Grid>
                <Grid item sm={12} md={4}>
                    <PopularMain title='연관 검색어' popularList={popularList} />
                </Grid>
            </Grid>
        </Box>
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
            <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
        </Box>
    </Box>
  )
}