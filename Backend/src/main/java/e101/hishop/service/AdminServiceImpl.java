package e101.hishop.service;

import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.User;
import e101.hishop.global.common.CommonException;
import e101.hishop.repository.AdminRepository;
import e101.hishop.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserJPARepository userJPARepository;

    @Override
    public Long savePay(Pay pays, Long userId, Long paymentId) {
        User user = userJPARepository.findById(userId)
                .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
        Card cards = adminRepository.findPaymentById(paymentId);
        //TODO setter 삭제하고 의도있게 작성
        pays.setUsersAndPay(user);
        pays.setPaymentAndPay(cards);
        return adminRepository.savePay(pays);
    }

    public List<PayInfoRespDto> getPayInfo() {
        return adminRepository.getPayInfo().stream()
                .map(PayInfoRespDto::of)
                .collect(Collectors.toList());
    }
}