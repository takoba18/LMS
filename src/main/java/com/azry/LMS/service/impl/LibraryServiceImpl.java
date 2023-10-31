package com.azry.LMS.service.impl;

import com.azry.LMS.config.SecurityUtils;
import com.azry.LMS.entity.Book;
import com.azry.LMS.entity.User;
import com.azry.LMS.model.BookEvent;
import com.azry.LMS.model.response.EditBookResponse;
import com.azry.LMS.repository.BookRepository;
import com.azry.LMS.repository.UserRepository;
import com.azry.LMS.service.JwtService;
import com.azry.LMS.service.LibraryService;
import com.azry.LMS.utils.enums.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookEventPublisher bookEventPublisher;

    @Async
    @Override
    @Transactional
    public EditBookResponse borrowBook(Long id) {
        Book book = bookRepo.findById((long) id).orElseThrow(() -> new RuntimeException("There is no book with that id"));


        if (book.getStatus().equals(BookStatus.AVAILABLE)) {

            User user = SecurityUtils.getAuthenticatedUser();
            book.setStatus(BookStatus.BORROWED);
            book.setUser(user);

            List<Book> borrowedBooks = user.getBorrowedBooks() != null ? user.getBorrowedBooks() : new ArrayList<>();

            borrowedBooks.add(book);
            user.setBorrowedBooks(borrowedBooks);

            userRepo.save(user);

            BookEvent bookBorrowedEvent = new BookEvent(id, "BORROW");
            bookEventPublisher.publishBookEvent(bookBorrowedEvent);
        } else throw new RuntimeException("This book is not available");


        return new EditBookResponse
                (bookRepo.save(book));

    }

    @Async
    @Transactional
    @Override
    public EditBookResponse returnBook(Long bookId) {
        Book book = bookRepo.findById((long) bookId).orElseThrow(() -> new RuntimeException("There is no book with that id"));

        User user = SecurityUtils.getAuthenticatedUser();
        List<Book> borrowedBooks = user.getBorrowedBooks() != null ? user.getBorrowedBooks() : new ArrayList<>();


        if (!borrowedBooks.contains(book)) throw new RuntimeException("You don't have access to this book");

        book.setStatus(BookStatus.AVAILABLE);

        borrowedBooks.remove(book);


        user = userRepo.save(user);
        book.setUser(null);

        BookEvent bookReturnedEvent = new BookEvent(bookId, "RETURN");
        bookEventPublisher.publishBookEvent(bookReturnedEvent);
        return new EditBookResponse(bookRepo.save(book));
    }
}
