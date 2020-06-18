package com.example.cs4550su1acabeyprojectserverjava.services;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import com.example.cs4550su1acabeyprojectserverjava.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {


    @Autowired
    WatchlistRepository repository;

    public Watchlist createWatchlist(Integer ownerId, Watchlist watchlist) {
        watchlist.setOwnerId(ownerId);
        return repository.save(watchlist);
    }

    public List<Watchlist> findAllWatchlists() {
        return repository.findAllWatchlists();
    }

    public Watchlist findWatchlistById(Integer watchlistId) {
        return repository.findWatchlistById(watchlistId);
    }

    public List<Watchlist> findWatchlistsForOwner(Integer ownerId) {
        return repository.findWatchlistsForOwner(ownerId);
    }

    public Integer deleteWatchlist(Integer watchlistId) {
        try {
            repository.deleteById(watchlistId);
            return 1;
        } catch (AssertionError e) {
            return 0;
        }
    }

    public Integer updateWatchlist(Integer watchlistId, Watchlist updatedWatchlist) {
        Watchlist watchlist = repository.findWatchlistById(watchlistId);

        watchlist.copyWatchlist(updatedWatchlist);
        watchlist.setId(watchlistId);

        try {
            repository.save(watchlist);
            return 1;
        } catch (AssertionError e) {
            return 0;
        }
    }

}
