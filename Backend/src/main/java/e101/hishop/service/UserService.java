package e101.hishop.service;

import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;

import java.util.List;

public interface UserService {

    Long saveCard(Cards cards, Long userId);
    List<CardInfoRespDto> cardInfo(Long userId);

    Boolean deleteCard(Long cardId);
    UserInfoRespDto getUserInfo(Long userPK);
    Users patchUserInfo(UserInfoReqDto dto, Long userPK);
    void deleteUserInfo(Long userPK);

    Boolean editName(EditNameReqDto dto, Long cardId);
}
