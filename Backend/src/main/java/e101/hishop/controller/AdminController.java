package e101.hishop.controller;

import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/pays")
  public ResponseEntity<List<PayInfoRespDto>> pays() {
        return new ResponseEntity<>(adminService.getPayInfo(), HttpStatus.OK);
//    public ResponseEntity<List<Map<String, Object>>> pays() {
//        List<Map<String, Object>> json = new ArrayList<>();
//        Map<String, Object> injson1 = new HashMap<>();
//        Map<String, Object> injson2 = new HashMap<>();
//        injson1.put("buyId", 60);
//        injson1.put("userName", "김싸피");
//        injson1.put("card", "삼성카드");
//        injson1.put("buyDate", "2022-01-01");
//        injson1.put("buyTotal", "12345");
//        injson2.put("buyId", 61);
//        injson2.put("userName", "이삼성");
//        injson2.put("card", "신한카드");
//        injson2.put("buyDate", "2022-01-25");
//        injson2.put("buyTotal", "123123");
//        json.add(injson1);
//        json.add(injson2);
//        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/pays/{buyId}")
    public ResponseEntity<List<Map<String, Object>>> paysDetail(@PathVariable String buyId) {
        List<Map<String, Object>> json = new ArrayList<>();
        Map<String, Object> injson1 = new HashMap<>();
        Map<String, Object> injson2 = new HashMap<>();
        Map<String, Object> injson3 = new HashMap<>();
        injson1.put("paydetailId", 49);
        injson1.put("buyId", 60);
        injson1.put("branchId", 357);
        injson1.put("productName", "과자");
        injson1.put("couponName", null);
        injson1.put("count", 3);
        injson1.put("price", 4500);
        injson2.put("paydetailId", 50);
        injson2.put("buyId", 60);
        injson2.put("branchId", 357);
        injson2.put("productName", "샴푸");
        injson2.put("couponName", null);
        injson2.put("count", 1);
        injson2.put("price", 5000);
        injson3.put("paydetailId", 51);
        injson3.put("buyId", 60);
        injson3.put("branchId", 357);
        injson3.put("productName", null);
        injson3.put("couponName", "과자 쿠폰");
        injson3.put("count", 1);
        injson3.put("price", -500);
        json.add(injson1);
        json.add(injson2);
        json.add(injson3);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Map<String, Object>>> items() {
        List<Map<String, Object>> json = new ArrayList<>();
        Map<String, Object> injson1 = new HashMap<>();
        Map<String, Object> injson2 = new HashMap<>();
        injson1.put("productId", 4234);
        injson1.put("name", "꺼깔콘");
        injson1.put("price", 3000);
        injson1.put("rfid", "3124875");
        injson1.put("barcode", null);
        injson1.put("discount", 0);
        injson1.put("image", "img.jpg");
        injson2.put("productId", 5137);
        injson2.put("name", "봐밤바");
        injson2.put("price", 1500);
        injson2.put("rfid", null);
        injson2.put("barcode", "8803154372");
        injson2.put("discount", 500);
        injson2.put("image", "img.jpg");
        json.add(injson1);
        json.add(injson2);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<Map<String, Object>> itemDetail(@PathVariable String itemId) {
        Map<String, Object> json = new HashMap<>();
        json.put("productId", 4234);
        json.put("name", "꺼깔콘");
        json.put("price", 3000);
        json.put("rfid", "3124875");
        json.put("barcode", null);
        json.put("discount", 0);
        json.put("image", "img.jpg");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PatchMapping("/items/{itemId}")
    public String itemModify(@PathVariable String itemId) {
        return "itemModify" + itemId;
    }

    @PostMapping("/items")
    public String itemCreate() {
        return "itemCreate";
    }

    @DeleteMapping("/items/{itemId}")
    public String itemDelete(@PathVariable String itemId) {
        return "itemDelete" + itemId;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> users() {
        List<Map<String, Object>> json = new ArrayList<>();
        Map<String, Object> injson1 = new HashMap<>();
        Map<String, Object> injson2 = new HashMap<>();
        injson1.put("loginId", "ssafy1234");
        injson1.put("password", "pass123");
        injson1.put("name", "김싸피");
        injson1.put("gender", "남");
        injson1.put("birthdate", "2023-01-01");
        injson1.put("phone", "010-1234-1234");
        injson1.put("email", "kimssafy@ssafy.com");
        injson1.put("adSelect", true);
        injson1.put("staffId", null);
        injson2.put("loginId", "ssafy7890");
        injson2.put("password", "pass789");
        injson2.put("name", "이싸피");
        injson2.put("gender", "여");
        injson2.put("birthdate", "2023-01-02");
        injson2.put("phone", "010-4567-4567");
        injson2.put("email", "leessafy@ssafy.com");
        injson2.put("adSelect", false);
        injson2.put("staffId", 365);
        json.add(injson1);
        json.add(injson2);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Map<String, Object>> userDetail(@PathVariable String userId) {
        Map<String, Object> json = new HashMap<>();
        json.put("loginId", "ssafy1234");
        json.put("password", "pass123");
        json.put("name", "김싸피");
        json.put("gender", "남");
        json.put("birthdate", "2023-01-01");
        json.put("phone", "010-1234-1234");
        json.put("email", "kimssafy@ssafy.com");
        json.put("adSelect", true);
        json.put("staffId", null);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}")
    public String userModify(@PathVariable String userId) {
        return "userModify" + userId;
    }

    @PostMapping("/users")
    public String userCreate() {
        return "userCreate";
    }

    @DeleteMapping("/users/{userId}")
    public String userDelete(@PathVariable String userId) {
        return "userDelete" + userId;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Map<String, Object>>> employees() {
        List<Map<String, Object>> json = new ArrayList<>();
        Map<String, Object> injson1 = new HashMap<>();
        Map<String, Object> injson2 = new HashMap<>();
        injson1.put("staffId", 365);
        injson1.put("branchId", 1);
        injson1.put("name", "이싸피");
        injson1.put("position", "사원");
        injson1.put("part", "영업");
        injson2.put("staffId", 456);
        injson2.put("branchId", 2);
        injson2.put("name", "박싸피");
        injson2.put("position", "대리");
        injson2.put("part", "유지보수");
        json.add(injson1);
        json.add(injson2);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Map<String, Object>> employeeDetail(@PathVariable String employeeId) {
        Map<String, Object> json = new HashMap<>();
        json.put("staffId", 365);
        json.put("branchId", 1);
        json.put("name", "이싸피");
        json.put("position", "사원");
        json.put("part", "영업");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PatchMapping("/employees/{employeeId}")
    public String employeeModify(@PathVariable String employeeId) {
        return "employeeModify" + employeeId;
    }

    @PostMapping("/employees")
    public String employeeCreate() {
        return "employeeCreate";
    }

    @DeleteMapping("/employees/{employeeId}")
    public String employeeDelete(@PathVariable String employeeId) {
        return "employeeDelete" + employeeId;
    }

}
