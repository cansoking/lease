package com.archie.lease.web.admin.service.impl;

import com.archie.lease.model.entity.SystemPost;
import com.archie.lease.web.admin.mapper.SystemPostMapper;
import com.archie.lease.web.admin.mapper.SystemUserMapper;
import com.archie.lease.model.entity.SystemUser;
import com.archie.lease.web.admin.service.SystemUserService;
import com.archie.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.archie.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【system_user(员工信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    @Autowired
    private SystemUserMapper mapper;

    @Autowired
    private SystemPostMapper systemPostMapper;

    @Override
    public IPage<SystemUserItemVo> pageSystemUser(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return mapper.pageSystemUser(page, queryVo);
    }

    @Override
    public SystemUserItemVo selectSystemUserById(Long id) {
        SystemUserItemVo result = new SystemUserItemVo();
        SystemUser systemUser = mapper.selectById(id);
        BeanUtils.copyProperties(systemUser, result);
        SystemPost systemPost = systemPostMapper.selectById(result.getPostId());
        result.setPostName(systemPost.getName());
        return result;
    }
}




