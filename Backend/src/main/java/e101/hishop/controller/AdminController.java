package e101.hishop.controller;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.response.PayDetailInfoRespDto;
import e101.hishop.domain.dto.response.PayInfoRespDto;
import e101.hishop.domain.dto.response.ProductRespDto;
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
    }

    @GetMapping("/pays/{buyId}")
    public ResponseEntity<List<PayDetailInfoRespDto>> paysDetail(@PathVariable Long buyId) {
        return new ResponseEntity<>(adminService.getPayDetail(buyId), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductRespDto>> products() {
        return new ResponseEntity<>(adminService.getProduct(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductRespDto> productDetail(@PathVariable Long productId) {
        return new ResponseEntity<>(adminService.getProductDetail(productId), HttpStatus.OK);
    }

    @PatchMapping("/product/{productId}")
    public ResponseEntity<String> productModify(@RequestBody ProductReqDto dto, @PathVariable Long productId) {
        adminService.editProduct(dto, productId);
        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> productCreate(@RequestBody ProductReqDto dto) {
        adminService.saveProduct(dto.toProductEntity());
        return new ResponseEntity<>("저장완료", HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> productDelete(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return new ResponseEntity<>("제거완료", HttpStatus.OK);
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
