package e101.hishop.service;

import e101.hishop.domain.entity.User;
import e101.hishop.global.common.CommonException;
import e101.hishop.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserJPARepository userJPARepository;

    @Override
    public Long signUp(User user){
        // userId 중복체크
        log.info("==========================signup {}", user);
        Boolean result = userJPARepository.existsByLoginId(user.getLoginId());
        if (result) throw new CommonException(1, "아이디가 중복됩니다.", HttpStatus.BAD_REQUEST);
        return userJPARepository.save(user).getId();
    }
}