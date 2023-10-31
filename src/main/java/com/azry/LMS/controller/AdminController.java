package com.azry.LMS.controller;

import com.azry.LMS.model.request.AddBookRequest;
import com.azry.LMS.model.request.EditBookRequest;
import com.azry.LMS.model.response.AddBookResponse;
import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/book")
    public  ResponseEntity<AddBookResponse> addNewBook(@RequestBody AddBookRequest req) {
        return ResponseEntity.ok(adminService.addBook(req));
    }

    @PatchMapping("/book")
    public ResponseEntity<EditBookResponse> editBook(@RequestBody EditBookRequest req) {
        return ResponseEntity.ok(adminService.editBook(req));
    }

    @DeleteMapping("/book/{id}")
    public void removeBook(@PathVariable Long id) {
        adminService.removeBook(id);
    }


}
