package e101.hishop.repository;


import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.AuthService;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CardRepositoryTest {
    @Autowired
    private UserService userService;
    @Autowired
    private  AuthService authService;

    @Test
    @Transactional
    public void testCardSave() {
        log.info("Card Save Start============================");
        Users users = Users.builder()
                .userId("USERID2222")
                .gender("MANNNN")
                .birthDate("19191919")
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .password("PASSWORD")
                .build();
        authService.signUp(users);


        userService.cardSave(Payment.builder()
                .cardNo("1234-1212-1111-1111")
                .name("삼성")
                .isDefault(false)
                .validDate("0121")
                .build(), users.getId());

        userService.cardSave(Payment.builder()
                .cardNo("1234-1212-1111-123")
                .name("신신")
                .isDefault(false)
                .validDate("0221")
                .build(), users.getId());

        assertThat(users.getPayments().size()).isEqualTo(2);
        log.info("UserCardSize is 2? ans:  {}============================", users.getPayments().size());
        log.info("Card Save End============================");

    }

}