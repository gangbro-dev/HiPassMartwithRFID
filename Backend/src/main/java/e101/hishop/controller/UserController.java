package e101.hishop.controller;

import e101.hishop.domain.dto.request.CardSaveReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.entity.Users;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userPK}")
    public ResponseEntity<Users> getUserInfo(@PathVariable Long userPK) {
        return new ResponseEntity<Users>(userService.getUserInfo(userPK), HttpStatus.OK);
    }

    @PatchMapping("/{userPK}")
    public String patchUserInfo(@RequestBody UserInfoReqDto dto, @PathVariable Long userPK) {
        userService.patchUserInfo(dto, userPK);
        return "수정 완료";
    }

    @DeleteMapping("/{userPK}")
    public String deleteUserInfo() {
        return "";
    }

    @GetMapping("/card/{userId}")
    public String userCardInfo(@PathVariable Long userId) {
        return "user card info Id" + " " + userId;
    }

    @PostMapping("/card/{userId}")
    public Long userCardSave(@PathVariable Long userId, @RequestBody CardSaveReqDto dto) {
        return userService.cardSave(dto.toPaymentEntity(), userId);
    }

    @PatchMapping("/card/{userId}")
    public String userCardEdit(@PathVariable Long userId) {
        return "user card edit Id" + " " + userId;
    }

    @DeleteMapping("/card/{userId}")
    public String userCardDelete(@PathVariable Long userId) {
        return "user card delete Id" + " " + userId;
    }

    @GetMapping("/{userId}/purchase")
    public String userPurchaseInfo(@PathVariable Long userId) {
        return "user purchase info Id" + " " + userId;
    }

    @GetMapping("/{userId}/purchase/{purchaseId}")
    public String userPurchaseDetail(@PathVariable Long userId, @PathVariable String purchaseId) {
        return "user purchase detail Id" + " " + userId + " " + purchaseId;
    }

    @PostMapping("/qr")
    public String qrCreate() {
        return "user QR";
    }
}
