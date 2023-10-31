package com.azry.LMS.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditBookRequest {
    private String author;
    private String title;
    private String ISBN;
}
