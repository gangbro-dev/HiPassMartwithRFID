package e101.hishop.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/pays")
    public String pays() {
        return "pays";
    }

    @GetMapping("/pays/{buyId}")
    public String paysDetail(@PathVariable String buyId) {
        return "pays" + buyId;
    }

    @GetMapping("/items")
    public String items() {
        return "items";
    }

    @GetMapping("/items/{itemId}")
    public String itemDetail(@PathVariable String itemId) {
        return "items" + itemId;
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
    public String users() {
        return "items";
    }

    @GetMapping("/users/{userId}")
    public String userDetail(@PathVariable String userId) {
        return "users" + userId;
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
    public String employees() {
        return "employees";
    }

    @GetMapping("/employees/{employeeId}")
    public String employeeDetail(@PathVariable String employeeId) {
        return "employees" + employeeId;
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
