package com.coderecipe.v1.auth.service;

import com.coderecipe.v1.auth.dto.vo.AuthReq.SignInReq;
import com.coderecipe.v1.auth.dto.vo.AuthRes.TokenRes;

public interface IAuthService {
    TokenRes requestSignIn(SignInReq req);
}
