package e101.hishop.init;

import e101.hishop.AppConfig;
import e101.hishop.domain.entity.*;
import e101.hishop.global.enumeration.Gender;
import e101.hishop.global.enumeration.Role;
import e101.hishop.repository.CardJPARepository;
import e101.hishop.repository.UserJPARepository;
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
    private CardJPARepository cardJPARepository;

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private AdminService adminService;

    //method invoked during the startup
    @PostConstruct
    public void loadData(){

        authService.signUp(User.builder()
                .loginId("user1234!")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1993,12,31))
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .password(AppConfig.testPasswordEncoder().encode("user1234!"))
                .build());

        userService.cardLoad(Card.builder()
                .cardNo("1234121211111111")
                .name("신한")
                .validDate("0121")
                .build(), 1L);



        userService.cardLoad(Card.builder()
                .cardNo("555121211111111")
                .name("국민")
                .validDate("0121")
                .build(), 1L);

        adminService.savePay(Pay.builder()
                .userName("NAME")
                .cardName("삼성")
                .buyDate("2022-09-01")
                .buyTotal(50000L)
                .build(), 1L);

        authService.signUp(User.builder()
                .loginId("admin1234!")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1999,12,31))
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .password(AppConfig.testPasswordEncoder().encode("admin1234!"))
                .role(Role.ROLE_ADMIN)
                .build());

        adminService.saveProduct(Product.builder()
                .name("세우깡")
                .price(1500L)
                .rfid("FC4947AC500104E0")
                .barcode("01243252")
                .image("")
                .build());

        adminService.saveProduct(Product.builder()
                .name("꺼깔콘")
                .price(2000L)
                .rfid("BB4247AC500104E0")
                .barcode("44103252")
                .image("")
                .build());

        adminService.saveProduct(Product.builder()
                .name("팝씨")
                .price(1530L)
                .rfid("FE9146AC500104E0")
                .barcode("44103252")
                .image("")
                .build());

        adminService.saveBranch(Branch.builder()
                .branchName("부산점")
                .region("부산광역시 강서구 녹산산업중로 333")
                .build());

        adminService.saveBranch(Branch.builder()
                .branchName("서울점")
                .region("서울시 강남구 테헤란로 212")
                .build());

        adminService.saveStaff(Staff.builder()
                .name("김싸피")
                .position("점장")
                .part("지점장")
                .staffLoginId("kimssafy")
                .build(), 9L);

        adminService.savePayDetail(PayDetail.builder()
                .productName("세우깡")
                .count(1)
                .price(1500L)
                .build(), 4L, 6L, 9L);

        adminService.savePayDetail(PayDetail.builder()
                .productName("꺼깔콘")
                .count(2)
                .price(4000L)
                .build(), 4L, 7L, 9L);

        adminService.saveKiosk(Kiosk.builder().build(), 9L);

        userService.cardLoad(Card.builder()
                .cardNo("1968267535975189")
                .name("사사")
                .validDate("2221")
                .build(), 5L);
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}