package com.example.server.service;

import com.example.server.pojo.LoginInformation;

public interface ILoginService {
    void saveUser(LoginInformation loginInformation);
}
