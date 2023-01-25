package e101.hishop.service;

import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.entity.Pay;

import java.util.List;

public interface AdminService {
    Long savePay(Pay pays, Long userId, Long paymentId);

    List<PayInfoRespDto> getPayInfo();
}
