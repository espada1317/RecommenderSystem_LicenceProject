package com.example.recsys.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityDto {

    private String title;

    private String type;

    private String poster;

    private String contentPosterLink;

    private Integer reviewScore;

    private String reviewMessage;

    private LocalDateTime localDateTime;

}
