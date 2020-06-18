package com.example.cs4550su1acabeyprojectserverjava.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String imageUrl;

    @OneToMany(mappedBy = "ownerId")
    private List<Watchlist> ownedWatchlists;

    @ManyToMany(mappedBy = "followers")
    private List<Watchlist> followedWatchlists;

    @ManyToMany(mappedBy = "friends")
    private List<User> friends;

}
