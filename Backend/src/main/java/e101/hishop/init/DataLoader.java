package e101.hishop.init;

import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Card;
import e101.hishop.domain.entity.User;
import e101.hishop.global.enumeration.Gender;
import e101.hishop.service.AdminService;
import e101.hishop.service.AuthService;
import e101.hishop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@Slf4j
public class DataLoader {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        authService.signUp(SignUpReqDto.builder()
                .loginId("USERID111")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1993,12,31))
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .password("PASSWORD")
                .build().toUsersEntity());

        userService.saveCard(Card.builder()
                .cardNo("1234-1212-1111-1111")
                .name("신한")
                .isDefault(true)
                .validDate("0121")
                .build(), 1L);

        userService.saveCard(Card.builder()
                .cardNo("4434-1212-1111-1111")
                .name("삼삼")
                .isDefault(false)
                .validDate("2221")
                .build(), 1L);

        adminService.savePay(Pay.builder()
                .buyDate("2022-09-01")
                .buyTotal(50000L)
                .build(), 1L, 2L);
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}