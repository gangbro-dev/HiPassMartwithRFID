package e101.hishop.controller;

import e101.hishop.domain.dto.request.LoginReqDto;
import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginReqDto dto) {
        boolean isLogin = authService.login(dto);
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
        authService.signUp(dto.toUsersEntity());
        return new ResponseEntity<>("가입완료", HttpStatus.OK);
    }

    @GetMapping("user/{userPK}")
    public ResponseEntity<Users> getUserInfo(@PathVariable Long userPK) {
        return new ResponseEntity<Users>(authService.getUserInfo(userPK), HttpStatus.OK);
    }

    @PatchMapping("user/{userPK}")
    public String patchUserInfo(@RequestBody UserInfoReqDto dto, @PathVariable Long userPK) {
        authService.patchUserInfo(dto, userPK);
        return "수정 완료";
    }

    @DeleteMapping("user/{userPK}")
    public String deleteUserInfo() {
        return "";
    }
}
