package com.azry.LMS.service;

import com.azry.LMS.model.response.EditBookResponse;
import org.springframework.stereotype.Service;

@Service
public interface LibraryService {
     EditBookResponse borrowBook(Long id);
     EditBookResponse returnBook(Long id);

}
