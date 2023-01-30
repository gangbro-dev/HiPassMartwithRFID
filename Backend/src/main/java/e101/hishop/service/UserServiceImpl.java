package e101.hishop.service;

import e101.hishop.domain.dto.request.EditNameReqDto;
//import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.User;
import e101.hishop.global.common.CommonException;
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
                    .cardNo(p.getCardNo())
                    .name(p.getName())
                    .validDate(p.getValidDate())
                    .build());
        }
        return respList;
    }

    @Override
    public Boolean deleteCard(Long cardId) {
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
}
