package com.example.recsys.service;

import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.entity.UserInfo;

import java.security.Principal;

public interface UserAuthService {

    boolean addUser(UserInfo userInfo);

    UserInfo findUserByNickname(String nickname);

    void updateUser(UserSettingsDto userSettingsDto, Principal principal);

}
