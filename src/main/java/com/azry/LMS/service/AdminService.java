package com.azry.LMS.service;

import com.azry.LMS.model.request.AddBookRequest;
import com.azry.LMS.model.request.EditBookRequest;
import com.azry.LMS.model.response.AddBookResponse;
import com.azry.LMS.model.response.EditBookResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
     EditBookResponse editBook(EditBookRequest req);
     void removeBook(Long id);
     AddBookResponse addBook(AddBookRequest req);
}
