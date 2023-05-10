interface Board {
    boardContent: string;
    boardImgUrl?: string | null;
    boardNumber: number;
    boardTitle: string;
    boardCate: string;
    boardWriteDatetime: string;
    commentCount: number;
    likeCount: number;
    hateCount: number;
    viewCount: number;
    writerEmail: string;
    writerNickname: string;
    writerProfileUrl?: string | null;
  }
  
  export default Board;
  