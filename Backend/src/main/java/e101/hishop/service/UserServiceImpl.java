package e101.hishop.service;

import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.UserJPARepository;
import e101.hishop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Long saveCard(Payment payment, Long userId) {
        Users users = userRepository.findUserById(userId);
        log.info("users, {}", users);
        payment.setUsersAndPayments(users);
        return userRepository.saveCard(payment);
    }
    @Override
    public List<CardInfoRespDto> cardInfo(Long userId) {
        List<CardInfoRespDto> respList = new ArrayList<>();
        List<Payment> list = userRepository.getCardInfo(userId);
        for (Payment p : list) {
            log.info("CARD_INFO=========================================================");
            log.info("{}", p);
            respList.add(CardInfoRespDto.builder()
                    .cardId(p.getId())
                    .cardNo(p.getCardNo())
                    .name(p.getName())
                    .isDefault(p.getIsDefault())
                    .validDate(p.getValidDate())
                    .build());
        }
        return respList;
    }


    public UserInfoRespDto getUserInfo(Long userPK) {
        Users user = userRepository.getUserInfo(userPK);

        return UserInfoRespDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .phone(user.getPhone())
                .adSelect(user.getAdSelect())
                .build();
    }

    public Users patchUserInfo(UserInfoReqDto dto, Long userPK){
        Users user = userJPARepository.findById(userPK).orElseThrow(() -> new EntityNotFoundException("Employee not found with id:"+userPK));
        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
//        user.setAdSelect(dto.getAdSelect());
        return userJPARepository.save(user);
    }

    public void deleteUserInfo(Long userPK){
        userJPARepository.deleteById(userPK);
    }
}
