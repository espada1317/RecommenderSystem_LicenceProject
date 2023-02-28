package com.example.recsys.service.implementation;

import com.example.recsys.entity.UserInfo;
import com.example.recsys.exceptions.UserAlreadyRegisteredException;
import com.example.recsys.repository.UserInfoRepository;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
