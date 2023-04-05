package com.example.recsys.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentReviewsDto {

    private Integer reviewScore;

    private String reviewMessage;

    private String title;

    private String link;

    private LocalDateTime localDateTime;

}
