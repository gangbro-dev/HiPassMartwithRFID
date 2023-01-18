package e101.hishop.controller;

import e101.hishop.domain.dto.request.SignUpReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.CommonService;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpReqDto dto) {
        commonService.signUp(dto.toUsersEntity());
        return "signup";
    }



}
