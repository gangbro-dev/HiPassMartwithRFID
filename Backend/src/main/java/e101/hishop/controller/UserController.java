package e101.hishop.controller;

import e101.hishop.domain.dto.request.CardSaveReqDto;
import e101.hishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{userId}")
    public String userInfo(@PathVariable Long userId) {
        return "user Id" + " " + userId;
    }

    @PatchMapping("/{userId}")
    public String userEdit(@PathVariable Long userId) {
        return "user edit Id" + " " + userId;
    }

    @DeleteMapping("/{userId}")
    public String userDelete(@PathVariable Long userId) {
        return "user delete Id" + " " + userId;
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
