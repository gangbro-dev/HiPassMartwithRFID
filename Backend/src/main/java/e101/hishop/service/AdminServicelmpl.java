package e101.hishop.service;

import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.entity.Pays;
import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;
import e101.hishop.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServicelmpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Long savePay(Pays pays, Long userId, Long paymentId) {
        Users users = adminRepository.findUserById(userId);
        Cards cards = adminRepository.findPaymentById(paymentId);
        pays.setUsersAndPay(users);
        pays.setPaymentAndPay(cards);
        return adminRepository.savePay(pays);
    }

    public List<PayInfoRespDto> getPayInfo() {
        List<Pays> pays = adminRepository.getPayInfo();

        List<PayInfoRespDto> otto = new ArrayList<>();
        for (Pays p : pays) {
            otto.add(PayInfoRespDto.builder()
                    .id(p.getId())
                    .users(p.getUsers())
                    .cards(p.getCards())
                    .buyDate(p.getBuyDate())
                    .buyTotal(p.getBuyTotal())
                    .build());
        }
        return otto;
    }

}
