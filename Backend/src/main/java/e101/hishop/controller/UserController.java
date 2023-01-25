package e101.hishop.controller;

import e101.hishop.domain.dto.request.CardSaveReqDto;
import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.global.common.CommonResponse;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

        @GetMapping("/{userId}")
        public ResponseEntity<UserInfoRespDto> getUserInfo(@PathVariable Long userId) {
            return new ResponseEntity<>(userService.getUserInfo(userId), HttpStatus.OK);
        }

    @PatchMapping("/{userId}")
    public CommonResponse updateUserInfo(@RequestBody UserInfoReqDto dto, @PathVariable Long userId) {
        return CommonResponse.builder()
                .data(Map.of("userId", userService.updateUserInfo(dto, userId)))
                .build();
    }

    @DeleteMapping("/{userId}")
    public String deleteUserInfo(@PathVariable Long userId) {
        userService.deleteUserInfo(userId);
        return "제거 완료";
    }

    @GetMapping("/{userId}/card")
    public ResponseEntity<List<CardInfoRespDto>> userCardInfo(@PathVariable Long userId) {
        List<CardInfoRespDto> payments = userService.cardInfo(userId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PostMapping("/{userId}/card")
    public ResponseEntity<String> userCardSave(@PathVariable Long userId, @RequestBody CardSaveReqDto dto) {
        userService.saveCard(dto.toPaymentEntity(), userId);
        return new ResponseEntity<>("저장완료", HttpStatus.OK);
    }

    @PatchMapping("/{userId}/card/{cardId}")
    public ResponseEntity<String> cardNameEdit(@RequestBody EditNameReqDto dto, @PathVariable Long userId, @PathVariable Long cardId) {
        userService.editName(dto, cardId);
        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/card/{cardId}")
    public ResponseEntity<String> userCardDelete(@PathVariable Long userId, @PathVariable Long cardId) {
        //TODO 카드아이디가 해당 유저 소속인지 유효성검사 필요
        userService.deleteCard(cardId);
        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
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
