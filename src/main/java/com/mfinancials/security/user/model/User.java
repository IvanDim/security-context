package com.mfinancials.security.user.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsImpl userDetails;

    public User(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    public User() {
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }
}
