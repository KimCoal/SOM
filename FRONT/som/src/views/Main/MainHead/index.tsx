import { Box, Grid, Typography } from '@mui/material';
import axios, { AxiosResponse } from 'axios';
import React, { useEffect, useState } from 'react'
import ResponseDto from 'src/apis/response';
import { GetTop12ListResponseDto } from 'src/apis/response/board'
import PreviewMain from 'src/components/PreviewMain';
import { GET_TOP12_LIST_URL } from 'src/constants/api';

export default function MainHead() {

    const [top12List, setTop12List] = useState<GetTop12ListResponseDto[]>([]);

    const getTop12List = () => {
        axios
            .get(GET_TOP12_LIST_URL)
            .then((response) => getTop12ListResponseHandler(response))
            .catch((error) => getTop12ListErrorHandler(error));
    };

    const getTop12ListResponseHandler = (response: AxiosResponse<any, any>) => {
        const { result, message, data } = response.data as ResponseDto<GetTop12ListResponseDto[]>;
        if (!result || data === null) return;
        setTop12List(data);
    };

    const getTop12ListErrorHandler = (error: any) => {
        console.log(error.message);
    };
    
    useEffect(() => {
        getTop12List();
    }, []);

    return (
        <Box sx={{pb: "40px",
                  pl: "120px",
                  pr: "120px",
                  backgroundImage: `url("https://dynaimage.cdn.cnn.com/cnn/c_fill,g_auto,w_1200,h_675,ar_16:9/https%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F171204142317-colorscope-orange-image-sunset.jpg")`,
                  backgroundPosition: '40% 60%',
                  }}>
          <Box sx={{ pt: "80px", pb: "32px", textAlign: "center" }}>
            <Typography sx={{ fontSize: "70px", fontWeight: 400, fontFamily: "fantasy" }}>
              Sound Of Music
            </Typography>
          </Box>
          <Box>
            <Typography
              sx={{
                fontSize: "24px",
                fontWeight: 400,
                p: "24px",
                textAlign: "center",
              }}
            >
              월간 TOP 12 게시물
            </Typography>
            <Grid container spacing={4}>
              {top12List.map((item) => (
                <Grid item sm={30} md={2} sx={{ borderRadius: '20px' }}>
                  <PreviewMain previewItem={item} />
                </Grid>
              ))}
            </Grid>
          </Box>
        </Box>
      );
}
