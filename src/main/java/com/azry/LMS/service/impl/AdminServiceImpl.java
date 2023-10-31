package com.azry.LMS.service.impl;

import com.azry.LMS.config.SecurityUtils;
import com.azry.LMS.entity.Book;
import com.azry.LMS.entity.User;
import com.azry.LMS.model.request.AddBookRequest;
import com.azry.LMS.model.request.EditBookRequest;
import com.azry.LMS.model.response.AddBookResponse;
import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.repository.BookRepository;
import com.azry.LMS.service.AdminService;
import com.azry.LMS.utils.enums.BookStatus;
import com.azry.LMS.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private BookRepository bookRepo;

    @Override
    public EditBookResponse editBook(EditBookRequest req) {
        checkAdminAccess();
        Book book = bookRepo.findByISBN(req.getISBN()).orElseThrow(() -> new RuntimeException("There is no book with that ISBN"));
        if (req.getAuthor() != null) book.setAuthor(req.getAuthor());
        if (req.getTitle() != null) book.setTitle(req.getTitle());
        if (req.getISBN() != null) book.setIsbn(req.getISBN());
        return new EditBookResponse(bookRepo.save(book));
    }

    @Override
    public void removeBook(Long id) {
        checkAdminAccess();
        bookRepo.delete(bookRepo.findById((long) id).orElseThrow(() -> new RuntimeException("There is no book with that id")));
    }

    @Override
    public AddBookResponse addBook(AddBookRequest req) {
        checkAdminAccess();
        Book book = new Book();
        if (!bookRepo.existsByISBN(req.getIsbn())) {
            book.setTitle(req.getTitle());
            book.setIsbn(req.getIsbn());
            book.setAuthor(req.getAuthor());
            book.setStatus(BookStatus.AVAILABLE);
        }
        return new AddBookResponse(bookRepo.save(book));
    }

    private void checkAdminAccess() {
        User user = SecurityUtils.getAuthenticatedUser();
        if (!user.getRole().equals(Role.ADMIN)) throw new RuntimeException("You don't have admin privileges");
    }
}
