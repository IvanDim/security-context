package com.mfinancials.security.user.service;

import com.google.common.collect.ImmutableMap;
import com.mfinancials.security.user.UserRepository;
import com.mfinancials.security.user.model.User;
import com.mfinancials.security.user.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
final class TokenAuthenticationService implements UserAuthenticationService {
    private final TokenService tokens;
    private final UserRepository userRepository;

    @Autowired
    TokenAuthenticationService(TokenService tokens, UserRepository userRepository) {
        this.tokens = tokens;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> login(final String username, final String password) {
        return Optional.ofNullable(userRepository
                .findByUserDetails_Username(username))
                .filter(user -> user.get().getUserDetails().getPassword().equals(password))
                .map(user -> tokens.expiring(ImmutableMap.of("username", username)));
    }

    @Override
    public User findByToken(final String token) {
        Map<String, String > result = tokens.verify(token);
        return userRepository.findByUserDetails_Username(result.get("username")).get();
    }

    @Override
    public void logout(final UserDetailsImpl user) {
        // Nothing to doy
    }
}
