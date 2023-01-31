package e101.hishop.init;

import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.entity.*;
import e101.hishop.global.enumeration.Gender;
import e101.hishop.global.enumeration.Role;
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
                .loginId("user1234!")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1993,12,31))
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .role(Role.ROLE_USER)
                .password("user1234!")
                .build().toUsersEntity());

        userService.saveCard(Card.builder()
                .cardNo("1234-1212-1111-1111")
                .name("신한")
                .validDate("0121")
                .build(), 1L);

        userService.saveCard(Card.builder()
                .cardNo("4434-1212-1111-1111")
                .name("삼삼")
                .validDate("2221")
                .build(), 1L);

        adminService.savePay(Pay.builder()
                .userName("NAME")
                .cardName("삼성")
                .buyDate("2022-09-01")
                .buyTotal(50000L)
                .build(), 1L, 2L);

        authService.signUp(SignUpReqDto.builder()
                .loginId("admin1234!")
                .gender(Gender.MALE)
                .birthDate(LocalDate.of(1999,12,31))
                .adSelect("YES")
                .email("EMAIL@naver.com")
                .name("NAME")
                .role(Role.ROLE_ADMIN)
                .password("admin1234!")
                .build().toUsersEntity());

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

        adminService.saveShopping(Shopping.builder()
                .count(2)
                .price(4000L)
                .build(), 14L, 7L);

        adminService.saveShopping(Shopping.builder()
                .count(1)
                .price(1500L)
                .build(), 14L, 6L);

        adminService.saveShopping(Shopping.builder()
                .count(1)
                .price(1430L)
                .build(), 14L, 8L);
    }

    //method invoked during the shutdown
//    @PreDestroy
//    public void removeData() {
//        userRepository.deleteAll();
//    }

}