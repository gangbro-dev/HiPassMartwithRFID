package e101.hishop.controller;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.CommonService;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommonController {

    private final CommonService commonService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReqDto dto) {
        boolean isLogin = commonService.login(dto);
        if (isLogin) {
        return new ResponseEntity<>("로그인 완료", HttpStatus.OK);
        } else {
        return new ResponseEntity<>("로그인 실패", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpReqDto dto) {
        commonService.signUp(dto.toUsersEntity());
        return new ResponseEntity<>("가입완료", HttpStatus.OK);
    }

}
