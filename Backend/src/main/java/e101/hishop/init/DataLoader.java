//package e101.hishop.init;
//
//import e101.hishop.domain.entity.Users;
//import e101.hishop.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements ApplicationRunner {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public void run(ApplicationArguments args) {
//        userRepository.saveUser(Users.builder()
//                .userId("USERID111")
//                .gender("MANNNN")
//                .birthDate("19191919")
//                .ad_select("YES")
//                .email("EMAIL")
//                .name("NAME")
//                .pwd("PASSWORD")
//                .build());
//    }
//}