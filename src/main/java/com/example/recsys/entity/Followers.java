package com.example.recsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "follower")
@Table(name = "follower")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Followers {

    @EmbeddedId
    private FollowerId id;

}
