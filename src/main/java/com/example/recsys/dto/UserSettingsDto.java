package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserSettingsDto {
    private String fullName;

    private String email;

    private String city;

    private String state;

    private String overview;
}
