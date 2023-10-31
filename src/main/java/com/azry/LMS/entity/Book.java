package com.azry.LMS.entity;
import com.azry.LMS.utils.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    @Column(unique = true)
    private String isbn;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id .equals( book.id);
    }
}
