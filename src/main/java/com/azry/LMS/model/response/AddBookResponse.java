package com.azry.LMS.model.response;

import com.azry.LMS.entity.Book;
import com.azry.LMS.model.request.AddBookRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddBookResponse extends AddBookRequest {
    private Long id;

    public AddBookResponse(Book book) {
        super(book.getAuthor(),book.getTitle(),book.getIsbn());
        id = book.getId();
    }
}
