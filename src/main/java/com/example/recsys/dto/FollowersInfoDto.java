package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowersInfoDto {

    private String nickname;
    private String fullName;
    private String photo;

}
