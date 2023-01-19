package e101.hishop.init;

import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.AuthService;
import e101.hishop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

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

        userService.cardSave(Payment.builder()
                .cardNo("1234-1212-1111-1111")
                .name("신한")
                .isDefault(false)
                .validDate("0121")
                .build(), 1L);
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}