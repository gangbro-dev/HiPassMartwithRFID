package e101.hishop.service;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.AuthRepository;
import e101.hishop.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final UserJPARepository userJPARepository;

    public boolean login(LoginReqDto dto) {
        return authRepository.login(dto);
    }

    @Override
    public boolean signUp(Users users) {

        //유효성 체크

        authRepository.signUp(users);
        return true;
    }

    public Users getUserInfo(Long userPK) {
        return authRepository.getUserInfo(userPK);
    }

    public Users patchUserInfo(UserInfoReqDto dto, Long userPK){
        Users user = userJPARepository.findById(userPK).orElseThrow(() -> new EntityNotFoundException("Employee not found with id:"+userPK));
        return userJPARepository.save(user);
    }
}
