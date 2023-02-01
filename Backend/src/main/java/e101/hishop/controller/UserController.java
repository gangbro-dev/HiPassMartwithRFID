package e101.hishop.controller;

import e101.hishop.domain.dto.request.CardSaveReqDto;
import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.dto.request.PayPasswordReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.CardInfoRespDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.UserInfoRespDto;
import e101.hishop.global.common.CommonResponse;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    private final UserInfoLoder userInfoLoder;
    @GetMapping()
    public ResponseEntity<UserInfoRespDto> getUserInfo() {
        return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
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
    public ResponseEntity<String> userCardSave(@PathVariable Long userId, @RequestBody @Validated CardSaveReqDto dto) {
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

    @PatchMapping("/{userId}/card/{cardId}/main")
    public ResponseEntity<String> cardMainEdit(@PathVariable Long userId, @PathVariable Long cardId) {
        userService.editMainCard(userId, cardId);
        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    @PatchMapping("/{userId}/card/password")
    public ResponseEntity<String> userPayPasswordEdit(@PathVariable Long userId, @RequestBody @Validated PayPasswordReqDto dto) {
            try {
                userService.editPayPassword(dto, userId);
                return new ResponseEntity<>("수정완료", HttpStatus.OK);
            } catch (NoSuchElementException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "비밀번호는 숫자 4자리여야 합니다.");
            }

    }

    @GetMapping("/{userId}/purchase")
    public ResponseEntity<List<PayInfoRespDto>> userPurchaseInfo(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserPay(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/purchase/{purchaseId}")
    public ResponseEntity<List<PayDetailInfoRespDto>> userPurchaseDetail(@PathVariable Long userId, @PathVariable Long purchaseId) {
        //TODO 유효성 검사
        return new ResponseEntity<>(userService.getPayDetail(purchaseId), HttpStatus.OK);
    }

    @PostMapping("/qr")
    public ResponseEntity<Map<String, Object>> qrCreate() {
        Map<String, Object> json = new HashMap<>();
        json.put("userId", "ssafy1234");
        json.put("datetime", "2023-01-12T14:38:27");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }


}
