package com.archie.lease.web.app.service.impl;

import com.archie.lease.web.app.mapper.ViewAppointmentMapper;
import com.archie.lease.model.entity.ViewAppointment;
import com.archie.lease.web.app.service.ApartmentInfoService;
import com.archie.lease.web.app.service.ViewAppointmentService;
import com.archie.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.archie.lease.web.app.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liubo
 * @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper mapper;

    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @Override
    public List<AppointmentItemVo> listAppointmentItemVo(Long userId) {
        return mapper.listAppointmentItemVo(userId);
    }

    @Override
    public AppointmentDetailVo getDetailByAppointmentId(Long id) {
        ViewAppointment viewAppointment = mapper.selectById(id);
        AppointmentDetailVo appointmentDetailVo = new AppointmentDetailVo();
        BeanUtils.copyProperties(viewAppointment, appointmentDetailVo);
        appointmentDetailVo.setApartmentItemVo(apartmentInfoService.selectApartmentItemVoById(viewAppointment.getApartmentId()));
        return appointmentDetailVo;
    }
}




