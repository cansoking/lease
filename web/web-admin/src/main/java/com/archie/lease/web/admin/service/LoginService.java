package com.archie.lease.web.admin.service;

import com.archie.lease.web.admin.vo.login.CaptchaVo;
import com.archie.lease.web.admin.vo.login.LoginVo;
import com.archie.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getLoginUserInfoById(Long userId);
}
