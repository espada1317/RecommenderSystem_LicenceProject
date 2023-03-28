package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "movie_preference")
@Table(name = "movie_preference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieGenresPreferences {

    @Id
    @Column(name = "preference_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preferenceKey;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "action")
    private Double actionRatio;

    @Column(name = "adventure")
    private Double adventureRatio;

    @Column(name = "animation")
    private Double animationRatio;

    @Column(name = "comedy")
    private Double comedyRatio;

    @Column(name = "crime")
    private Double crimeRatio;

    @Column(name = "documentary")
    private Double documentaryRatio;

    @Column(name = "drama")
    private Double dramaRatio;

    @Column(name = "family")
    private Double familyRatio;

    @Column(name = "fantasy")
    private Double fantasyRatio;

    @Column(name = "history")
    private Double historyRatio;

    @Column(name = "horror")
    private Double horrorRatio;

    @Column(name = "music")
    private Double musicRatio;

    @Column(name = "mystery")
    private Double mysteryRatio;

    @Column(name = "romance")
    private Double romanceRatio;

    @Column(name = "science_fiction")
    private Double scienceFictionRatio;

    @Column(name = "thriller")
    private Double thrillerRatio;

    @Column(name = "tv_movie")
    private Double tvMovieRatio;

    @Column(name = "war")
    private Double warRatio;

    @Column(name = "western")
    private Double westernRatio;
}