package com.azry.LMS.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddBookRequest {
        private String author;
        private String title;
        private String isbn;
}
