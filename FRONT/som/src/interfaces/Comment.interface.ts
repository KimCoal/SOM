interface Comment {
    commentNumber: number;
    parentCommentNumber: number | null;
    writerEmail: string;
    boardNumber: number;
    writeDatetime: string;
    commentContent: string;
    writerProfileUrl?: string | null;
    writerNickname: string;
}

export default Comment;