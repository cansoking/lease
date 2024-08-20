package com.archie.lease.web.app.controller.appointment;


import com.archie.lease.common.login.LoginUserHolder;
import com.archie.lease.web.app.service.ViewAppointmentService;
import com.archie.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.archie.lease.web.app.vo.appointment.AppointmentItemVo;
import com.archie.lease.common.result.Result;
import com.archie.lease.model.entity.ViewAppointment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "看房预约信息")
@RestController
@RequestMapping("/app/appointment")
public class ViewAppointmentController {

    @Autowired
    private ViewAppointmentService service;

    @Operation(summary = "保存或更新看房预约")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ViewAppointment viewAppointment) {
        service.saveOrUpdate(viewAppointment);
        return Result.ok();
    }

    @Operation(summary = "查询个人预约看房列表")
    @GetMapping("listItem")
    public Result<List<AppointmentItemVo>> listItem() {
        Long userId = LoginUserHolder.getLoginUser().getUserId();
        List<AppointmentItemVo> result = service.listAppointmentItemVo(userId);
        return Result.ok(result);
    }

    @GetMapping("getDetailById")
    @Operation(summary = "根据ID查询预约详情信息")
    public Result<AppointmentDetailVo> getDetailById(Long id) {
        AppointmentDetailVo result = service.getDetailByAppointmentId(id);
        return Result.ok(result);
    }

}

