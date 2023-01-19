package e101.hishop.service;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean login(LoginReqDto dto);
    boolean signUp(Users users);
}
