package com.archie.lease.web.app.service.impl;

import com.archie.lease.web.app.mapper.RoomInfoMapper;
import com.archie.lease.web.app.service.RoomInfoService;
import com.archie.lease.model.entity.RoomInfo;
import com.archie.lease.web.app.vo.room.RoomItemVo;
import com.archie.lease.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
@Slf4j
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    @Autowired
    private RoomInfoMapper mapper;

    @Override
    public IPage<RoomItemVo> getRoomItemPage(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return mapper.getRoomItemPage(page, queryVo);
    }
}




