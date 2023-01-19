package e101.hishop.controller;

import e101.hishop.domain.dto.request.CardSaveReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userPK}")
    public ResponseEntity<UserInfoRespDto> getUserInfo(@PathVariable Long userPK) {
        return new ResponseEntity<>(userService.getUserInfo(userPK), HttpStatus.OK);
    }

    @PatchMapping("/{userPK}")
    public String patchUserInfo(@RequestBody UserInfoReqDto dto, @PathVariable Long userPK) {
        userService.patchUserInfo(dto, userPK);
        return "수정 완료";
    }

    @DeleteMapping("/{userPK}")
    public String deleteUserInfo(@PathVariable Long userPK) {
        userService.deleteUserInfo(userPK);
        return "제거 완료";
    }

    @GetMapping("/{userId}/card")
    public ResponseEntity<List<CardInfoRespDto>> userCardInfo(@PathVariable Long userId) {
        List<CardInfoRespDto> payments = userService.cardInfo(userId);
        return new ResponseEntity<>(payments , HttpStatus.OK);
    }

    @PostMapping("/{userId}/card")
    public ResponseEntity<String> userCardSave(@PathVariable Long userId, @RequestBody CardSaveReqDto dto) {
        userService.saveCard(dto.toPaymentEntity(), userId);
        return new ResponseEntity<>("저장완료" , HttpStatus.OK);
    }

    @PatchMapping("/{userId}/card")
    public String userCardEdit(@PathVariable Long userId) {
        return "user card edit Id" + " " + userId;
    }

    @DeleteMapping("/{userId}/card/{cardId}")
    public String userCardDelete(@PathVariable Long userId, @PathVariable Long cardId) {
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
