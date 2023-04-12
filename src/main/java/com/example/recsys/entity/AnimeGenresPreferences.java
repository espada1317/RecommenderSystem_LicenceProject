package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "anime_preference")
@Table(name = "anime_preference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimeGenresPreferences {

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

    @Column(name = "avant_garde")
    private Double avantGardeRatio;

    @Column(name = "cars")
    private Double carsRatio;

    @Column(name = "comedy")
    private Double comedyRatio;

    @Column(name = "crime")
    private Double crimeRatio;

    @Column(name = "demons")
    private Double demonsRatio;

    @Column(name = "drama")
    private Double dramaRatio;

    @Column(name = "fantasy")
    private Double fantasyRatio;

    @Column(name = "game")
    private Double gameRatio;

    @Column(name = "gourmet")
    private Double gourmetRatio;

    @Column(name = "historical")
    private Double historicalRatio;

    @Column(name = "horror")
    private Double horrorRatio;

    @Column(name = "josei")
    private Double joseiRatio;

    @Column(name = "kids")
    private Double kidsRatio;

    @Column(name = "martial_arts")
    private Double martialArtsRatio;

    @Column(name = "mecha")
    private Double mechaRatio;

    @Column(name = "military")
    private Double militaryRatio;

    @Column(name = "music")
    private Double musicRatio;

    @Column(name = "mystery")
    private Double mysteryRatio;

    @Column(name = "parody")
    private Double parodyRatio;

    @Column(name = "police")
    private Double policeRatio;

    @Column(name = "psychological")
    private Double psychologicalRatio;

    @Column(name = "romance")
    private Double romanceRatio;

    @Column(name = "samurai")
    private Double samuraiRatio;

    @Column(name = "school")
    private Double schoolRatio;

    @Column(name = "sci_fi")
    private Double scienceFictionRatio;

    @Column(name = "seinen")
    private Double seinenRatio;

    @Column(name = "shoujo")
    private Double shoujoRatio;

    @Column(name = "shounen")
    private Double shounenRatio;

    @Column(name = "slice_of_life")
    private Double sliceOfLifeRatio;

    @Column(name = "space")
    private Double spaceRatio;

    @Column(name = "sports")
    private Double sportsRatio;

    @Column(name = "super_power")
    private Double superPowerRatio;

    @Column(name = "supernatural")
    private Double supernaturalRatio;

    @Column(name = "suspense")
    private Double suspenseRatio;

    @Column(name = "vampire")
    private Double vampireRatio;

    @Column(name = "work_life")
    private Double workLifeRatio;

}