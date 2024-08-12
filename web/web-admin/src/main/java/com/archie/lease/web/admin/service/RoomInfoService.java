package com.archie.lease.web.admin.service;

import com.archie.lease.model.entity.RoomInfo;
import com.archie.lease.web.admin.vo.room.RoomDetailVo;
import com.archie.lease.web.admin.vo.room.RoomItemVo;
import com.archie.lease.web.admin.vo.room.RoomQueryVo;
import com.archie.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface RoomInfoService extends IService<RoomInfo> {

    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getRoomDetailById(Long id);

    void removeItemById(Long id);

    void saveOrUpdateRoomItem(RoomSubmitVo roomSubmitVo);
}
