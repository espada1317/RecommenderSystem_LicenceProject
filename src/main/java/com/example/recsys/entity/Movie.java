package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "moviekey", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieKey;

    @Column(name = "release", nullable = false)
    private LocalDate release;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "overview", length = 2000)
    private String overview;

    @Column(name = "length")
    private int length;

    @Column(name = "popularity")
    private double popularity;

    @Column(name = "votecount")
    private int votecount;

    @Column(name = "voteaverage")
    private double voteaverage;

    @Column(name = "language")
    private String language;

    @Column(name = "genres")
    private String genres;

    @Column(name = "poster")
    private String poster;

}