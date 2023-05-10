interface Dto {
    board: {
      boardContent: string;
      boardImgUrl: string | null;
      boardNumber: number;
      boardTitle: string;
      boardCate: string;
      boardWriteDatetime: string;
      commentCount: number;
      parentCommentNumeber: number | null;
      likeCount: number;
      hateCount: number;
      viewCount: number;
      writerEmail: string;
      writerNickname: string;
      writerProfileUrl: string | null;
    };
    commentList: [
      {
        boardNumber: number;
        commentContent: string;
        commentNumber: number;
        parentCommentNumber: number | null;
        writeDatetime: string;
        writerEmail: string;
        writerNickname: string;
        writerProfileUrl: string | null;
      }
    ];
    likeList: [
      {
        boardNumber: number;
        userEmail: string;
        userNickname: string;
        userProfileUrl: string | null;
      }
    ];
    hateList: [
      {
        boardNumber: number;
        userEmail: string;
        userNickname: string;
        userProfileUrl: string | null;
      }
    ];
  }
  
  export default Dto;
  