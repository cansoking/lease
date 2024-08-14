package com.archie.lease.web.admin.service;

import com.archie.lease.model.entity.UserInfo;
import com.archie.lease.web.admin.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【user_info(用户信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface UserInfoService extends IService<UserInfo> {

    IPage<UserInfo> listByqueryVo(Page<UserInfo> page, UserInfoQueryVo queryVo);
}
