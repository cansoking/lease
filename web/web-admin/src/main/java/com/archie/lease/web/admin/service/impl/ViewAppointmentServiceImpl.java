package com.archie.lease.web.admin.service.impl;

import com.archie.lease.model.entity.ViewAppointment;
import com.archie.lease.web.admin.mapper.ViewAppointmentMapper;
import com.archie.lease.web.admin.service.ViewAppointmentService;
import com.archie.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.archie.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public IPage<AppointmentVo> listByPage(Page<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.listByPage(page, queryVo);
    }
}




