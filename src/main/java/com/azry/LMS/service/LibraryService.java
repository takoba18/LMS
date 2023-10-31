package com.azry.LMS.service;

import com.azry.LMS.model.response.EditBookResponse;
import org.springframework.stereotype.Service;

@Service
public interface LibraryService {
     EditBookResponse borrowBook(int id);
     EditBookResponse returnBook(int id);

}
