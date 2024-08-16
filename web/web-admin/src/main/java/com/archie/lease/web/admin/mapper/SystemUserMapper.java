package com.archie.lease.web.admin.mapper;

import com.archie.lease.model.entity.SystemUser;
import com.archie.lease.web.admin.vo.login.LoginVo;
import com.archie.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.archie.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    SystemUser selectOneUserByUsername(String username);
}




