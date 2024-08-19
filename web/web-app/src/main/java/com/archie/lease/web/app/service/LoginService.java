package com.archie.lease.web.app.service;

import com.archie.lease.web.app.vo.user.LoginVo;
import com.archie.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
    void getCode(String phone);

    String login(LoginVo loginVo);

    UserInfoVo getLoginInfoByUserId(Long userId);
}
