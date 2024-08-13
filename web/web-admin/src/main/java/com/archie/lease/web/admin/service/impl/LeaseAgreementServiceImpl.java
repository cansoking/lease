package com.archie.lease.web.admin.service.impl;

import com.archie.lease.model.entity.*;
import com.archie.lease.web.admin.mapper.*;
import com.archie.lease.web.admin.service.LeaseAgreementService;
import com.archie.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.archie.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【lease_agreement(租约信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    @Autowired
    private LeaseAgreementMapper mapper;

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public IPage<AgreementVo> pageAgreementVo(Page<AgreementVo> page, AgreementQueryVo queryVo) {
        return mapper.pageAgreementVo(page, queryVo);
    }

    @Override
    public AgreementVo selectAgreementVoById(Long id) {
        LeaseAgreement leaseAgreement = mapper.selectById(id);
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(leaseAgreement.getApartmentId());
        RoomInfo roomInfo = roomInfoMapper.selectById(leaseAgreement.getRoomId());
        PaymentType paymentType = paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId());
        LeaseTerm leaseTerm = leaseTermMapper.selectById(leaseAgreement.getLeaseTermId());
        AgreementVo result = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreement, result);
        result.setApartmentInfo(apartmentInfo);
        result.setRoomInfo(roomInfo);
        result.setPaymentType(paymentType);
        result.setLeaseTerm(leaseTerm);
        return result;
    }
}




