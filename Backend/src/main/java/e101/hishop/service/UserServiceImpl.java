package e101.hishop.service;

import e101.hishop.domain.dto.request.EditNameReqDto;
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
import e101.hishop.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserJPARepository userJPARepository;
    private final PayJPARepository payJPARepository;

    private final PayDetailJPARepository payDetailJPARepository;
    private final CardJPARepository cardJPARepository;


    @Override
    public Card saveCard(Card cards) {
        User user = userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        log.info("user, {}", user);
        cards.setUsersAndCards(user);
        return cardJPARepository.save(cards);
    }

    @Override
    public Card cardLoad(Card cards, Long id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        log.info("user, {}", user);
        cards.setUsersAndCards(user);
        return cardJPARepository.save(cards);
    }


    @Override
    public List<CardInfoRespDto> cardInfo() {
        List<CardInfoRespDto> respList = new ArrayList<>();
        List<Card> list = cardJPARepository.findAllByUserId(getUserId());
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
    public Boolean deleteCard(Long cardId) {
        User user = userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        Card card = cardJPARepository.findById(cardId)
                .orElseThrow(() -> new CommonException(2, "Card객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));;
        if (!card.getUser().equals(user)) {
            throw new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (user.getDefaultCardId() == cardId) {
            editMainCard(null);
        }
        cardJPARepository.deleteById(cardId);
        return true;
    }

    @Override
    public void editMainCard(Long cardId){
        User user = userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        user.changeDefaultCard(cardId);
    }

    @Override
    public Boolean editPayPassword(PayPasswordReqDto dto){
        User user = userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        user.changePayPassword(dto);
        return true;
    }

    @Override
    public UserInfoRespDto getUserInfo() {
        User user = userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        return UserInfoRespDto.of(user);
    }

    @Override
    public Long updateUserInfo(UserInfoReqDto dto) {
        return userJPARepository.findById(getUserId())
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR))
                .updateUserInfo(dto)
                .getId();
    }

    @Override
    public void deleteUserInfo() {
        userJPARepository.deleteById(getUserId());
    }

    @Override
    public Boolean editName(EditNameReqDto dto, Long cardId) {
        Card card = cardJPARepository.findById(cardId)
                .orElseThrow(() -> new CommonException(2, "Card객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        card.setName(dto.getName());
        return true;
    }

    @Override
    public List<PayInfoRespDto> getUserPay() {
        List<Pay> pay = payJPARepository.findAllByUserId(getUserId());
        List<PayInfoRespDto> payList = new ArrayList<>();
        for (Pay p: pay) {
            payList.add(PayInfoRespDto.of(p));
        }
        return payList;
    }

    @Override
    public List<PayDetailInfoRespDto> getPayDetail(Long purchaseId) {
        List<PayDetail> payDetails = payDetailJPARepository.findAllByPayId(purchaseId);
        List<PayDetailInfoRespDto> payDetailList = new ArrayList<>();
        for (PayDetail p: payDetails) {
            payDetailList.add(PayDetailInfoRespDto.of(p));
        }
        return payDetailList;
    }

    @Override
    public Long getUserId() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        User user = userJPARepository.findByLoginId(username)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));

        return user.getId();
    }
}
