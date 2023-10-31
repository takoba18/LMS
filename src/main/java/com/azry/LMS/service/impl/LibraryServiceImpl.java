package com.azry.LMS.service.impl;

import com.azry.LMS.entity.Book;
import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.repository.BookRepository;
import com.azry.LMS.service.LibraryService;
import com.azry.LMS.utils.enums.BookStatus;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {
    private BookRepository bookRepo;
    @Override
    public EditBookResponse borrowBook(int id) {
        Book book = bookRepo.findById((long) id).orElseThrow(() -> new RuntimeException("There is no book with that id"));
        book.setStatus(BookStatus.BORROWED);
        //book.setBorrowUserId();
        return new EditBookResponse(bookRepo.save(book));
    }

    @Override
    public EditBookResponse returnBook(int id) {
        Book book = bookRepo.findById((long) id).orElseThrow(() -> new RuntimeException("There is no book with that id"));
        book.setStatus(BookStatus.AVAILABLE);
        //book.setBorrowUserId();
        return new EditBookResponse(bookRepo.save(book));
    }
}
