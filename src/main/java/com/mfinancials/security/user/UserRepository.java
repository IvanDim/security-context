package com.mfinancials.security.user;


import com.mfinancials.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserDetails_Username(String username);
}
