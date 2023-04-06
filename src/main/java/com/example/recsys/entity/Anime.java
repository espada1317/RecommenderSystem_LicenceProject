package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "anime")
@Table(name = "anime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @Column(name = "anime_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animeKey;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "source")
    private String source;

    @Column(name = "average_runtime")
    private Integer averageRuntime;

    @Column(name = "no_episodes")
    private Integer numberEpisodes;

    @Column(name = "runtime_start")
    private LocalDate runtimeStart;

    @Column(name = "runtime_end")
    private LocalDate runtimeEnd;

    @Column(name = "genres")
    private String genres;

    @Column(name = "vote_count")
    private Integer voteCount;

    @Column(name = "vote_total")
    private Double voteTotal;

    @Column(name = "poster")
    private String poster;

    @Column(name = "link")
    private String link;

}