package com.azry.LMS.entity;
import com.azry.LMS.utils.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    @Column(unique = true)
    private String ISBN;
    @Column(columnDefinition = "varchar(15) default 'AVAILABLE'")
    private BookStatus status;
    private int borrowUserId;

}
