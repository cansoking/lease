package com.archie.lease.web.admin.service.impl;

import com.archie.lease.web.admin.mapper.UserInfoMapper;
import com.archie.lease.web.admin.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.archie.lease.model.entity.UserInfo;
import com.archie.lease.web.admin.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【user_info(用户信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Override
    public IPage<UserInfo> listByqueryVo(Page<UserInfo> page, UserInfoQueryVo queryVo) {
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(queryVo.getPhone() != null, UserInfo::getPhone, queryVo.getPhone())
                .eq(queryVo.getStatus() != null, UserInfo::getStatus, queryVo.getStatus());
        return super.page(page, wrapper);
    }
}




