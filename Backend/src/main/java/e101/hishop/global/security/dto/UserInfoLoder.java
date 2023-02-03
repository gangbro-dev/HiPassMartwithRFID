//package e101.hishop.global.security.dto;
//
//import e101.hishop.repository.UserJPARepository;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@RequiredArgsConstructor
//public class UserInfoLoder {
//
//    private final UserJPARepository userJPARepository;

//    private UserDetails userDetails =
//            (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    private String name = userDetails.getUsername();
//
//    private User user = userJPARepository.findByLoginId("user")
//            .orElseThrow(() -> new CommonException(2, "User객체가 존재하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR));
//
//    private Long userId = user.getId();
//}
