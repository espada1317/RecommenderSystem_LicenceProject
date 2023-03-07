package com.example.recsys.repository;

import com.example.recsys.entity.UserInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByNickname(String username);

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.fullName = ?1, u.userLocation = ?2, u.email = ?3, u.userOverview = ?4 WHERE userId = ?5")
    void updateUserInfo(String fullName, String userLocation, String email, String overview, Integer userId);
}