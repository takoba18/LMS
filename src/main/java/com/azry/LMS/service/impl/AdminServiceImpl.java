package com.azry.LMS.service.impl;

import com.azry.LMS.entity.Book;
import com.azry.LMS.model.request.AddBookRequest;
import com.azry.LMS.model.request.EditBookRequest;
import com.azry.LMS.model.response.AddBookResponse;
import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.repository.BookRepository;
import com.azry.LMS.service.AdminService;
import com.azry.LMS.utils.enums.BookStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private BookRepository bookRepo;
    @Override
    public EditBookResponse editBook(EditBookRequest req) {
        Book book = bookRepo.findByISBN(req.getISBN()).orElseThrow(() -> new RuntimeException("There is no book with that ISBN"));
        if(req.getAuthor()!=null) book.setAuthor(req.getAuthor());
        if(req.getTitle()!=null) book.setTitle(req.getTitle());
        if(req.getISBN()!=null) book.setISBN(req.getISBN());
        return new EditBookResponse(bookRepo.save(book));
    }

    @Override
    public void removeBook(int id) {
        bookRepo.delete(bookRepo.findById((long) id).orElseThrow(() -> new RuntimeException("There is no book with that id")));
    }

    @Override
    public AddBookResponse addBook(AddBookRequest req) {
        Book book = new Book();
        if(!bookRepo.existsByISBN(req.getISBN())){
            book.setTitle(req.getTitle());
            book.setISBN(req.getISBN());
            book.setAuthor(req.getAuthor());
            book.setStatus(BookStatus.AVAILABLE);
        }
        return new AddBookResponse(bookRepo.save(book));
    }
}
