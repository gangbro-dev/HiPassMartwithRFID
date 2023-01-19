package e101.hishop.init;

import e101.hishop.domain.entity.Users;
import e101.hishop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private AuthService authService;

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
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}