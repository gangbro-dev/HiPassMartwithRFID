package e101.hishop.service;

import e101.hishop.domain.dto.request.EditNameReqDto;
//import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.PayDetail;
import e101.hishop.domain.entity.User;
import e101.hishop.global.common.CommonException;
import e101.hishop.repository.PayDetailJPARepository;
import e101.hishop.repository.PayJPARepository;
import e101.hishop.repository.UserJPARepository;
import e101.hishop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserJPARepository userJPARepository;
    private final PayJPARepository payJPARepository;
    private final PayDetailJPARepository payDetailJPARepository;


    @Override
    public Long saveCard(Card cards, Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        log.info("user, {}", user);
        cards.setUsersAndCards(user);
        return userRepository.saveCard(cards);
    }

    @Override
    public List<CardInfoRespDto> cardInfo(Long userId) {
        List<CardInfoRespDto> respList = new ArrayList<>();
        List<Card> list = userRepository.getCardInfo(userId);
        for (Card p : list) {
            log.info("CARD_INFO=========================================================");
            log.info("{}", p);
            respList.add(CardInfoRespDto.builder()
                    .cardId(p.getId())
                    .cardNo(p.getCardNo().substring(0, 4))
                    .name(p.getName())
                    .build());
        }
        return respList;
    }

    @Override
    public Boolean deleteCard(Long userId, Long cardId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        Card card = userRepository.findCardById(cardId);
        if (!card.getUser().equals(user)) {
            throw new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (user.getDefaultCardId() == cardId) {
            editMainCard(userId, null);
        }
        userRepository.deleteCard(cardId);
        return true;
    }

    public void editMainCard(Long userId, Long cardId){
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        user.changeDefaultCard(cardId);
    }

    public Boolean editPayPassword(PayPasswordReqDto dto, Long userId){
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        user.changePayPassword(dto);
        return true;
    }

    public UserInfoRespDto getUserInfo(Long userId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return UserInfoRespDto.of(user);
    }

    public Long updateUserInfo(UserInfoReqDto dto, Long userId) {
        return userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateUserInfo(dto)
                .getId();
    }

    public void deleteUserInfo(Long userPK) {
        userJPARepository.deleteById(userPK);
    }

    @Override
    public Boolean editName(EditNameReqDto dto, Long cardId) {
        return userRepository.editName(cardId, dto);
    }

    public List<PayInfoRespDto> getUserPay(Long userId) {
        List<Pay> pay = payJPARepository.findAllByUserId(userId);
        List<PayInfoRespDto> payList = new ArrayList<>();
        for (Pay p: pay) {
            payList.add(PayInfoRespDto.of(p));
        }
        return payList;
    }

    public List<PayDetailInfoRespDto> getPayDetail(Long purchaseId) {
        List<PayDetail> payDetails = payDetailJPARepository.findAllByPayId(purchaseId);
        List<PayDetailInfoRespDto> payDetailList = new ArrayList<>();
        for (PayDetail p: payDetails) {
            payDetailList.add(PayDetailInfoRespDto.of(p));
        }
        return payDetailList;
    }

}
