package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tv_preference")
@Table(name = "tv_preference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TvGenresPreferences {

    @Id
    @Column(name = "preference_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer preferenceKey;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "skipped")
    private Integer skipped;

    @Column(name = "action")
    private Double actionRatio;

    @Column(name = "adventure")
    private Double adventureRatio;

    @Column(name = "animation")
    private Double animationRatio;

    @Column(name = "biography")
    private Double biographyRatio;

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

    @Column(name = "game_show")
    private Double gameShowRatio;

    @Column(name = "history")
    private Double historyRatio;

    @Column(name = "horror")
    private Double horrorRatio;

    @Column(name = "music")
    private Double musicRatio;

    @Column(name = "musical")
    private Double musicalRatio;

    @Column(name = "mystery")
    private Double mysteryRatio;

    @Column(name = "news")
    private Double newsRatio;

    @Column(name = "reality_tv")
    private Double realityTvRatio;

    @Column(name = "romance")
    private Double romanceRatio;

    @Column(name = "science_fiction")
    private Double scienceFictionRatio;

    @Column(name = "sport")
    private Double sportRatio;

    @Column(name = "talk_show")
    private Double talkShowRatio;

    @Column(name = "thriller")
    private Double thrillerRatio;

    @Column(name = "war")
    private Double warRatio;

    @Column(name = "western")
    private Double westernRatio;

}