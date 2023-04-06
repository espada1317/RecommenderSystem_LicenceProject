package com.example.recsys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity(name = "anime_reviews")
@Table(name = "anime_reviews")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimeReview {

    @Id
    @Column(name = "anime_review_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animeReviewKey;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "category")
    private String category;

    @Column(name = "review_score")
    private Integer reviewScore;

    @Column(name = "review_message")
    private String reviewMessage;

    @Column(name = "datetime")
    private LocalDateTime localDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "anime_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Anime anime;

}