package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalTimeStatsDto {

    private int days;
    private int hours;
    private int minutes;

}