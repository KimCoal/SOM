interface RequestDto {
    boardNumber: number;
    parentCommentNumber: number | null;
    commentContent: string;
}

export default RequestDto;