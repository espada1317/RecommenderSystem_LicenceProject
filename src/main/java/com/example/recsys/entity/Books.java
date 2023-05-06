package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "books")
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @Column(name = "book_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookKey;

    @Column(name = "book_authors", length = 1000)
    private String authors;

    @Column(name = "book_desc", length = 4000)
    private String description;

    @Column(name = "book_pages")
    private String numPages;

    @Column(name = "book_rating")
    private Float rating;

    @Column(name = "book_rating_count")
    private Integer ratingCount;

    @Column(name = "book_title", length = 1000)
    private String title;

    @Column(name = "genres")
    private String genres;

    @Column(name = "poster_url")
    private String poster;
}