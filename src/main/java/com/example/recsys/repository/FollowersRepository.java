package com.example.recsys.repository;

import com.example.recsys.entity.FollowerId;
import com.example.recsys.entity.Followers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, FollowerId> {

    @Query(value = "SELECT * FROM follower WHERE username = :nickname AND follower = :follower", nativeQuery = true)
    Followers checkForExistingFollower(String nickname, String follower);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follower f WHERE f.id.username = ?1 AND f.id.follower = ?2")
    void deleteFollower(String username, String follower);

    @Query(value = "SELECT * FROM follower WHERE username = :nickname", nativeQuery = true)
    List<Followers> getAllFollowersForUser(String nickname);

}
