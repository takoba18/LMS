package com.azry.LMS.model.response;


import com.azry.LMS.entity.Book;
import com.azry.LMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class UserDTO {

    private String username;
    private List<Book> borrowedBooks;

    public static UserDTO convertFromUser(User user){
        return UserDTO.builder().username(user.getUsername()).borrowedBooks(user.getBorrowedBooks()).build();
    }

}
