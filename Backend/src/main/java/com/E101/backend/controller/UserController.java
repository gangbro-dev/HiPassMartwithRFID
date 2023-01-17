package e101.hishop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/{userId}")
    public String userInfo(@PathVariable String userId) {
        return "user Id" + " " + userId;
    }

    @PatchMapping("/{userId}")
    public String userEdit(@PathVariable String userId) {
        return "user edit Id" + " " + userId;
    }

    @DeleteMapping("/{userId}")
    public String userDelete(@PathVariable String userId) {
        return "user delete Id" + " " + userId;
    }

    @GetMapping("/card/{userId}")
    public String userCardInfo(@PathVariable String userId) {
        return "user card info Id" + " " + userId;
    }

    @PostMapping("/card/{userId}")
    public String userCardSave(@PathVariable String userId) {
        return "user card save Id" + " " + userId;
    }

    @PatchMapping("/card/{userId}")
    public String userCardEdit(@PathVariable String userId) {
        return "user card edit Id" + " " + userId;
    }

    @DeleteMapping("/card/{userId}")
    public String userCardDelete(@PathVariable String userId) {
        return "user card delete Id" + " " + userId;
    }

    @GetMapping("/{userId}/purchase")
    public String userPurchaseInfo(@PathVariable String userId) {
        return "user purchase info Id" + " " + userId;
    }

    @GetMapping("/{userId}/purchase/{purchaseId}")
    public String userPurchaseDetail(@PathVariable String userId, @PathVariable String purchaseId) {
        return "user purchase detail Id" + " " + userId + " " + purchaseId;
    }

    @PostMapping("/qr")
    public String qrCreate() {
        return "user QR";
    }
}
