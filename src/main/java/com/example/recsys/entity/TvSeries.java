package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tv_series")
@Table(name = "tv_series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvSeries {

    @Id
    @Column(name = "tv_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tvKey;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "overview", length = 2000)
    private String overview;

    @Column(name = "runtime_start")
    private Integer runtimeStart;

    @Column(name = "runtime_end")
    private Integer runtimeEnd;

    @Column(name = "average_runtime")
    private Integer averageRuntime;

    @Column(name = "no_episodes")
    private Integer numberEpisodes;

    @Column(name = "genres")
    private String genres;

    @Column(name = "votecount")
    private Integer voteCount;

    @Column(name = "voteaverage")
    private Double voteAverage;

    @Column(name = "poster")
    private String poster;

    @Column(name = "star1")
    private String star1;

    @Column(name = "star2")
    private String star2;

    @Column(name = "star3")
    private String star3;

    @Column(name = "star4")
    private String star4;

}