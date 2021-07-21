package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookComment")
@NamedEntityGraph(name = "BookCommentWithBook",
        attributeNodes = {@NamedAttributeNode(value = "book")})
public class BookComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCommentId;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "bookID")
    private Book book;

    @Column(name = "comment", nullable = false)
    private String comment;
}
