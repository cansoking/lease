package com.archie.lease.web.app.service.impl;

import com.archie.lease.common.constant.RedisConstant;
import com.archie.lease.common.exception.LeaseException;
import com.archie.lease.common.result.ResultCodeEnum;
import com.archie.lease.common.utils.CodeUtils;
import com.archie.lease.common.utils.JwtUtils;
import com.archie.lease.model.entity.UserInfo;
import com.archie.lease.model.enums.BaseStatus;
import com.archie.lease.web.app.mapper.UserInfoMapper;
import com.archie.lease.web.app.service.LoginService;
import com.archie.lease.web.app.service.LoginService;
import com.archie.lease.web.app.service.SmsService;
import com.archie.lease.web.app.vo.user.LoginVo;
import com.archie.lease.web.app.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SmsService smsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void getCode(String phone) {
        String randomCode = CodeUtils.getRandomCode(6);
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;

        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (hasKey) {
            Long ttl = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (RedisConstant.APP_LOGIN_CODE_TTL_SEC - ttl < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        stringRedisTemplate.opsForValue().set(key, randomCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
        smsService.sendCode(phone, randomCode);
    }

    @Override
    public String login(LoginVo loginVo) {
        if (loginVo.getPhone() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if (loginVo.getCode() == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone();
        String code = stringRedisTemplate.opsForValue().get(key);
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }

        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getPhone, loginVo.getCode());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfo.setNickname("用户-" + loginVo.getPhone().substring(7));
            userInfoMapper.insert(userInfo);
        } else {
            if (userInfo.getStatus() == BaseStatus.DISABLE) {
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }

        return JwtUtils.createToken(userInfo.getId(), userInfo.getPhone());
    }

    @Override
    public UserInfoVo getLoginInfoByUserId(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return new UserInfoVo(userInfo.getNickname(), userInfo.getAvatarUrl());
    }
}
