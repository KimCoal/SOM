import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

import axios, { AxiosResponse } from 'axios';
import { Box, Grid, Pagination, Stack, Typography } from '@mui/material'

import BoardList from 'src/components/BoardList';
import { getPageCount } from 'src/utils';
import { usePagingHook } from 'src/hooks';
import ResponseDto from 'src/apis/response';
import { GetCateListResponseDto,} from 'src/apis/response/board';
import { GET_CATE_LIST_URL} from 'src/constants/api';

export default function CateView() {

    const { category } = useParams();
    const { viewList, pageNumber, boardList, setBoardList, onPageHandler, COUNT } = usePagingHook(5);

    let loadFlag = true;

    console.log(category);

    const getCateList = () => {
        axios.get(GET_CATE_LIST_URL(category as string))
            .then((response) => getCateListResponseHandler(response))
            .catch((error) => getCateListErrorHandler(error));
    }

    const getCateListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetCateListResponseDto[]>;
        if (!result || data == null) return;
        setBoardList(data);
    }

    const getCateListErrorHandler = (error: any) => {
        console.log(error.message);
    }

    useEffect(() => {
        if (loadFlag) {
            loadFlag = false;
            getCateList();
        }
    }, [category]);

  return (
    <Box sx={{ p: '40px 120px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
        <Box sx={{ fontSize: '24px', fontWeight: 500 }}>
            <Box component='strong'>{category}</Box>
            <Typography component='span' sx={{ fontSize: '24px', fontWeight: 500, opacity: 0.7 }}> 카테고리 게시글 목록입니다. </Typography>
            <Box component='strong'>{boardList.length}</Box>
        </Box>
        <Box sx={{ pt: '20px', pb: '80px' }}>
            <Grid container spacing={3}>
                <Grid item sm={12} md={8}>
                    <Stack spacing={2}>
                        {viewList.length === 0 ? (<Box sx={{ height: '416px', display: 'flex', justifyContent: 'center', alignItems: 'center' }}><Typography sx={{ fontSize: '24px', fontWeight: 500, color: 'rgba(0, 0, 0, 0.4)' }}>카테고리 게시물이 없습니다.</Typography></Box>) : viewList.map((boardItem) => (<BoardList item={boardItem as GetCateListResponseDto} />))}
                    </Stack>
                </Grid>
            </Grid>
        </Box>
        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
            <Pagination page={pageNumber} count={getPageCount(boardList, COUNT)} onChange={(event, value) => onPageHandler(value)} />
        </Box>
    </Box>
  )
}
