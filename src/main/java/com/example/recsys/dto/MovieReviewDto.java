package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDto {
    private String category;

    private Integer scoreReview;

    private String reviewMessage;
}
