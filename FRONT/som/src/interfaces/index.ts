import Board from './Board.interface';
import Comment from './Comment.interface';
import Likes from './Like.interface';
import User from './User.interface';
import Hate from './Hate.inerface';

export interface IPreviewItem {
    boardNumber: number;
    img: string | null;
    writerProfile: string;
    writerNickname: string;
    writeDate: string;
    boardTitle: string;
    boardCate: string;
    boardContent: string;
    likeCount: number;
    hateCount: number;
    commentCount: number;
    viewCount: number;
}

export interface IUser {
    email: string;
    password: string;
    nickname: string;
    telNumber: string;
    address: string;
    addressDetail: string;
    profile?: string;
}

export interface ILikeUser {
    likeUserProfile: string;
    likeUserNickname: string;
}

export interface IHateUser {
    hateUserProfile: string;
    hateUserNickname: string;
}

export interface ICommentItem {
    commentUserProfile: string;
    commentUserNickname: string;
    commentContent: string;
    commentDatetime: string;
}

export type { Board, Comment, Likes, Hate, User };