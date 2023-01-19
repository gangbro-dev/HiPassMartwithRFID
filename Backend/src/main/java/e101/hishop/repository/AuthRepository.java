package e101.hishop.repository;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.entity.Users;

public interface AuthRepository {
    public boolean login(LoginReqDto dto);
    public Long signUp(Users users);
    public Users getUserInfo(Long userPK);
}
