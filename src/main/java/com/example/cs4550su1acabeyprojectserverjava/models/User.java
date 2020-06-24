package com.example.cs4550su1acabeyprojectserverjava.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany
    @JoinTable(
            name="FOLLOW_WATCHLIST",
            joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="WATCHLIST_ID", referencedColumnName="ID"))
    private List<Watchlist> followedWatchlists;

    @ManyToMany
    @JoinTable(
            name="FOLLOW_USER",
            joinColumns=@JoinColumn(name="USER_A_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="USER_B_ID", referencedColumnName="ID"))
    private List<User> followers;

    public User() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.password = "";
        this.role = "";
        this.bio = "";
        this.imageUrl = "";
    }

    public User copyUser(User other) {
        this.id = other.getId();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.email = other.getEmail();
        this.role = other.getRole();
        this.bio = other.getBio();
        this.imageUrl = other.getImageUrl();
        this.created = other.getCreated();
        this.lastUpdated = other.getLastUpdated();
        this.ownedWatchlists = other.getOwnedWatchlists();
        this.followedWatchlists = other.getFollowedWatchlists();
        this.followers = other.getFollowers();
        return this;
    }

    public User anonymize() {
        this.password = "";
        this.email = "";
        this.ownedWatchlists = this.getPublicOwnedWatchlists();
        this.followedWatchlists = this.getPublicFollowedWatchlists();

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role.equals(user.role) &&
                Objects.equals(bio, user.bio) &&
                Objects.equals(imageUrl, user.imageUrl) &&
                Objects.equals(ownedWatchlists, user.ownedWatchlists) &&
                Objects.equals(followedWatchlists, user.followedWatchlists) &&
                Objects.equals(followers, user.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, role, bio, imageUrl, created, lastUpdated, ownedWatchlists, followedWatchlists, followers);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<Watchlist> getOwnedWatchlists() {
        return ownedWatchlists;
    }

    public List<Watchlist> getPublicOwnedWatchlists() {
        List<Watchlist> publics = new ArrayList<>();

        for (Watchlist w : this.ownedWatchlists) {
            if (!w.getPrivate()) {
                publics.add(w);
            }
        }

        return publics;

    }

    public void setOwnedWatchlists(List<Watchlist> ownedWatchlists) {
        this.ownedWatchlists = ownedWatchlists;
    }

    public List<Watchlist> getFollowedWatchlists() {
        return followedWatchlists;
    }

    public List<Watchlist> getPublicFollowedWatchlists() {
        List<Watchlist> publics = new ArrayList<>();

        for (Watchlist w : this.followedWatchlists) {
            if (!w.getPrivate()) {
                publics.add(w);
            }
        }
        return publics;
    }

    public void setFollowedWatchlists(List<Watchlist> followedWatchlists) {
        this.followedWatchlists = followedWatchlists;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
