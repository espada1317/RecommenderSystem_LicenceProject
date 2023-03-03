package com.example.recsys.service.implementation;

import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.entity.UserInfo;
import com.example.recsys.exceptions.UserAlreadyRegisteredException;
import com.example.recsys.repository.UserInfoRepository;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserAuthImpl implements UserAuthService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserInfo userInfo) {
        if(validateRegister(userInfo)) {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
        }
    }

    public boolean validateRegister(UserInfo userInfo) {
        String nickname = userInfo.getNickname();
        if(userInfoRepository.findByNickname(nickname).isPresent())
            throw new UserAlreadyRegisteredException(nickname);
        return true;
    }

    @Override
    public UserInfo findUserByNickname(String nickname) {
        return userInfoRepository.findByNickname(nickname).get();
    }

    @Override
    public void updateUser(UserSettingsDto userSettingsDto, Principal principal) {
        Optional<UserInfo> userInfo = userInfoRepository.findByNickname(principal.getName());

        if(userInfo.isPresent()) {
            UserInfo modifiedUser = userInfo.get();

            String modifiedLocation = userSettingsDto.getCity() + ", " + userSettingsDto.getState();
            String modifiedEmail = userSettingsDto.getEmail();
            String modifiedFullName = userSettingsDto.getFullName();

            userInfoRepository.updateUserInfo(modifiedFullName, modifiedLocation, modifiedEmail, modifiedUser.getUserId());
        }
    }

}
