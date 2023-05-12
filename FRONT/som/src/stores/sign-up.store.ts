import { create } from "zustand";

interface ISignUpStore {
    email: string;
    password: string;
    passwordCheck: string;
    nickname: string;
    telNumber: string;
    address: string;
    addressDetail: string;
    emailMessage: string;
    setEmail: (str: string) => void;
    setPassword: (str: string) => void;
    setPasswordCheck: (str: string) => void;
    setNickname: (str: string) => void;
    setTelNumber: (str: string) => void
    setAddress: (str: string) => void;
    setAddressDetail: (str: string) => void;

    signUpError: boolean;
    setSignUpError: (signUpError: boolean) => void;

    emailPatternCheck: boolean | null;
    setEmailPatternCheck: (emailPatternCheck: boolean) => void;
    emailValidate: boolean | null;
    setEmailValidate: (emailValidate: boolean) => void;

    passwordPatternCheck: boolean | null;
    setPasswordPatternCheck: (passwordPatternCheck: boolean) => void;
    passwordValidate: boolean | null;
    setPasswordValidate: (passwordValidate: boolean) => void;
    
    nicknameValidate: boolean | null;
    setNicknameValidate: (nicknameValidate: boolean) => void;

    telNumberPatternCheck: boolean | null;
    setTelNumberPatternCheck: (telNumberPatternCheck: boolean) => void;
    telNumberValidate: boolean | null;
    setTelNumberValidate: (telNumberValidate: boolean) => void;
}

const useStore = create<ISignUpStore>((set) => ({
    email: '',
    password: '',
    passwordCheck: '',
    nickname: '',
    telNumber: '',
    address: '',
    addressDetail: '',
    emailMessage: '',
    setEmail: (email) => {
        const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
        const isMatched = emailValidator.test(email);
        const emailMessage = isMatched ? '' : '이메일 주소 포맷이 맞지 않습니다.';
        set((state) => ({...state, email, emailMessage}))
    },
    setPassword: (password) => set((state) => ({...state, password})),
    setPasswordCheck: (passwordCheck) => set((state) => ({...state, passwordCheck})),
    setNickname: (nickname) => set((state) => ({...state, nickname})),
    setTelNumber: (telNumber) => set((state) => ({...state, telNumber})),
    setAddress: (address) => set((state) => ({...state, address})),
    setAddressDetail: (addressDetail) => set((state) => ({...state, addressDetail})),

    signUpError: false,
    setSignUpError: (signUpError: boolean) => set((state) => ({...state, signUpError})),

    emailPatternCheck: null,
    setEmailPatternCheck: (emailPatternCheck: boolean) => set((state) => ({...state, emailPatternCheck})),
    emailValidate: null,
    setEmailValidate: (emailValidate: boolean) => set((state) => ({...state, emailValidate})),

    passwordPatternCheck: null,
    setPasswordPatternCheck: (passwordPatternCheck: boolean) => set((state) => ({...state, passwordPatternCheck})),
    passwordValidate: null,
    setPasswordValidate: (passwordValidate: boolean) => set((state) => ({...state, passwordValidate})),

    nicknameValidate: null,
    setNicknameValidate: (nicknameValidate: boolean) => set((state) => ({...state, nicknameValidate})),

    telNumberPatternCheck: null,
    setTelNumberPatternCheck: (telNumberPatternCheck: boolean) => set((state) => ({...state, telNumberPatternCheck})),
    telNumberValidate: null,
    setTelNumberValidate: (telNumberValidate: boolean) => set((state) => ({...state, telNumberValidate})),
}))

export default useStore;