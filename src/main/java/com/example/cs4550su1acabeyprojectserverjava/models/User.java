package com.example.cs4550su1acabeyprojectserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String bio;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "ownerId")
    private List<Watchlist> ownedWatchlists;

    @ManyToMany(mappedBy = "followers")
    @JoinTable(
            name="FOLLOW_WATCHLIST",
            joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="WATCHLIST_ID", referencedColumnName="ID"))
    private List<Watchlist> followedWatchlists;

    @ManyToMany(mappedBy = "followers")
    @JoinTable(
            name="FOLLOW_USER",
            joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"))
    private List<User> followers;

}
