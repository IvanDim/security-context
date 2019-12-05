package com.mfinancials.security.user;

import com.mfinancials.security.user.model.User;
import com.mfinancials.security.user.model.UserDetailsImpl;
import com.mfinancials.security.user.service.NotificationService;
import com.mfinancials.security.user.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserAuthenticationService userAuthenticationService;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserRepository userRepository, UserAuthenticationService userAuthenticationService, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.userAuthenticationService = userAuthenticationService;
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    String register(
            @RequestBody UserDetailsImpl userDetails) {
        userRepository.save(new User(userDetails));
        notificationService.userRegistered(userDetails);
        return login(userDetails);
    }

    @PostMapping("/login")
    String login(@RequestBody UserDetailsImpl user) {
        return userAuthenticationService
                .login(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @GetMapping("/validate")
    String validateTokenAndGetUsername(@RequestHeader("Authorization") String token) {
         User user = userAuthenticationService.findByToken(token);
        if (user != null) {
            return user.getUserDetails().getUsername();
        }

        throw new RuntimeException();
    }
}
