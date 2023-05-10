interface Dto {
    boardContent: string;
    boardImgUrl: string | null;
    boardNumber: number;
    boardTitle: string;
    boardWriteDatetime: string;
    boardCate: string;
    commentCount: number;
    likeCount: number;
    hateCount: number;
    viewCount: number;
    writerNickname: string;
    writerProfileUrl: string | null;
}

export default Dto;