package e101.hishop.service;

import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.UserJPARepository;
import e101.hishop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserJPARepository userJPARepository;

    @Override
    public Long cardSave(Payment payment, Long userId) {
        Users users = userRepository.findUserById(userId);
        log.info("users, {}", users);
        payment.setUsersAndPayments(users);
        return userRepository.saveCard(payment);
    }

    public Users getUserInfo(Long userPK) {
        return userRepository.getUserInfo(userPK);
    }

    public Users patchUserInfo(UserInfoReqDto dto, Long userPK){
        Users user = userJPARepository.findById(userPK).orElseThrow(() -> new EntityNotFoundException("Employee not found with id:"+userPK));
        return userJPARepository.save(user);
    }
}
