package e101.hishop.service;

import e101.hishop.domain.dto.request.SignUpReqDto;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    boolean signUp(SignUpReqDto dto);
}
