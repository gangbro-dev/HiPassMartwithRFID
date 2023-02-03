package e101.hishop.controller;

import e101.hishop.domain.dto.request.ProductReqDto;
import e101.hishop.domain.dto.request.StaffReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import e101.hishop.domain.dto.response.*;
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
    public ResponseEntity<List<UserInfoRespDto>> users() {
        return new ResponseEntity<>(adminService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserInfoRespDto> userDetail(@PathVariable Long userId) {
        return new ResponseEntity<>(adminService.getUser(userId), HttpStatus.OK);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<String> userModify(@RequestBody UserInfoReqDto dto, @PathVariable Long userId) {
        adminService.modifyUser(dto, userId);
        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> userDelete(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return new ResponseEntity<>("제거완료", HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<StaffRespDto>> employees() {
        return new ResponseEntity<>(adminService.getStaff(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<StaffRespDto> employeeDetail(@PathVariable Long employeeId) {
        return new ResponseEntity<>(adminService.getStaffDetail(employeeId), HttpStatus.OK);
    }

    @PatchMapping("/employees/{employeeId}")
    public ResponseEntity<String> employeeModify(@RequestBody StaffReqDto dto, @PathVariable Long employeeId) {
        //TODO 지점 변경?
        adminService.modifyStaff(dto, employeeId);
        return new ResponseEntity<>("수정완료", HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> employeeCreate(@RequestBody StaffReqDto dto) {
        Long branchId = dto.getBranchId();
        adminService.saveStaff(dto.toStaffEntity(), branchId);
        return new ResponseEntity<>("생성완료", HttpStatus.OK);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> employeeDelete(@PathVariable Long employeeId) {
        adminService.deleteStaff(employeeId);
        return new ResponseEntity<>("제거완료", HttpStatus.OK);
    }

}
