package com.azry.LMS.model.response;

import com.azry.LMS.entity.Book;
import com.azry.LMS.model.request.EditBookRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EditBookResponse extends EditBookRequest {
    private int id;

    public EditBookResponse(Book book) {
        super(book.getAuthor(),book.getTitle(),book.getISBN());
        id = book.getId();
    }
}
