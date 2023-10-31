package com.azry.LMS.controller;

import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/borrow/{id}")
    public ResponseEntity<EditBookResponse> borrowBook(@PathVariable Long id) {
        return ResponseEntity.ok(libraryService.borrowBook(id));
    }

    @PostMapping("/return/{id}")
    public void returnBook(@PathVariable Long id) {
        libraryService.returnBook(id);
    }
}
