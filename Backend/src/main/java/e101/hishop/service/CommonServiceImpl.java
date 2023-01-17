package e101.hishop.service;

import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.CommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommonServiceImpl implements CommonService{

    private final CommonRepository commonRepository;

    @Override
    public boolean signUp(SignUpReqDto dto) {

        //유효성 체크

        commonRepository.signUp(Users.builder()
                .userId(dto.getUserId())
                .pwd(dto.getPwd())
                .name(dto.getName())
                .gender(dto.getGender())
                .birthDate(dto.getBirthDate())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .ad_select(dto.getAd_select())
                .build());
        return true;
    }
}
