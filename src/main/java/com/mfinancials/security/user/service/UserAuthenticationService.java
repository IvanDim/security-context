package com.mfinancials.security.user.service;


import com.mfinancials.security.user.model.User;
import com.mfinancials.security.user.model.UserDetailsImpl;

import java.util.Optional;

public interface UserAuthenticationService   {

    Optional<String> login(String username, String password);
    User findByToken(String token);
    void logout(UserDetailsImpl user);
}
