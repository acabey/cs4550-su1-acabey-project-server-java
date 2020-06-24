package com.example.cs4550su1acabeyprojectserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="watchlists")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Integer ownerId;


    private Boolean isPrivate;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @ManyToMany
    @JsonIgnore
    private List<User> followers;

    @ManyToMany
    @JoinTable(
            name="WATCH_MEDIA",
            joinColumns=@JoinColumn(name="MEDIA_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="WATCHLIST_ID", referencedColumnName="ID"))
    private List<Medium> media;

    public Watchlist() {
        this.id = 0;
        this.title = "";
        this.ownerId = 0;
        this.isPrivate = false;
        this.created = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.followers = new ArrayList<User>();
    }

    public Watchlist copyWatchlist(Watchlist other) {
        this.setMedia(other.getMedia());
        this.setFollowers(other.getFollowers());
        this.setOwnerId(other.getOwnerId());
        this.setPrivate(other.getPrivate());
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }
}
