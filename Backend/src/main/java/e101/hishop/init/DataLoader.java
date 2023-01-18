package e101.hishop.init;

import e101.hishop.domain.entity.Users;
import e101.hishop.repository.CommonRepository;
import e101.hishop.repository.UserRepository;
import e101.hishop.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

@Component
public class DataLoader {

    @Autowired
    private CommonService commonService;

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        commonService.signUp(Users.builder()
                .userId("USERID111")
                .gender("MANNNN")
                .birthDate("19191919")
                .ad_select("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .pwd("PASSWORD")
                .build());
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}