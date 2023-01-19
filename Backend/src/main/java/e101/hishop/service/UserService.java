package e101.hishop.service;

import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;

import java.util.List;

public interface UserService {

    Long saveCard(Payment payment, Long userId);
    Users getUserInfo(Long userPK);
    Users patchUserInfo(UserInfoReqDto dto, Long userPK);
    List<CardInfoRespDto> cardInfo(Long userId);
}
