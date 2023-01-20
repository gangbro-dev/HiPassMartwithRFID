package e101.hishop.init;

import e101.hishop.domain.entity.Pays;
import e101.hishop.domain.entity.Cards;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.AdminService;
import e101.hishop.service.AuthService;
import e101.hishop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        authService.signUp(Users.builder()
                .userId("USERID111")
                .gender("MANNNN")
                .birthDate("19191919")
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .password("PASSWORD")
                .build());

        userService.saveCard(Cards.builder()
                .cardNo("1234-1212-1111-1111")
                .name("신한")
                .isDefault(true)
                .validDate("0121")
                .build(), 1L);

        userService.saveCard(Cards.builder()
                .cardNo("4434-1212-1111-1111")
                .name("삼삼")
                .isDefault(false)
                .validDate("2221")
                .build(), 1L);

        adminService.savePay(Pays.builder()
                .buy_date("2022-09-01")
                .buy_total(50000L)
                .build(), 1L, 2L);
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}