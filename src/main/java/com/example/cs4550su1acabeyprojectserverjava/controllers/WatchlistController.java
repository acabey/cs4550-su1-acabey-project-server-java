package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import com.example.cs4550su1acabeyprojectserverjava.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WatchlistController {

    @Autowired
    WatchlistService watchlistService;

    @PostMapping("/api/users/{ownerId}/watchlists")
    public Watchlist createWatchlist(
            @PathVariable Integer ownerId,
            @RequestBody Watchlist watchlist) {
        return watchlistService.createWatchlist(ownerId, watchlist);
    }

    @DeleteMapping("/api/watchlists/{watchlistId}")
    public Integer deleteWatchlist(
            @PathVariable Integer watchlistId) {
        return watchlistService.deleteWatchlist(watchlistId);
    }

    @PutMapping("/api/watchlists/{watchlistId}")
    public Integer deleteWatchlist(
            @PathVariable Integer watchlistId,
            @RequestBody Watchlist newWatchlist) {
        return watchlistService.updateWatchlist(watchlistId, newWatchlist);
    }

    @GetMapping("/api/users/{ownerId}/watchlists")
    public List<Watchlist> getWatchlistsForOwner(@PathVariable Integer ownerId) {
        return watchlistService.findWatchlistsForOwner(ownerId);
    }

    /**
     * For moderator / administrator: finds all watchlists
     * For user: finds public watchlists + their own watchlists
     * For anonymous: finds public watchlists
     * @return
     */
    @GetMapping("/api/watchlists")
    public List<Watchlist> findAllWatchlists() {
        return watchlistService.findAllWatchlists();
    }

    @GetMapping("/api/watchlists/{watchlistId}")
    public Watchlist findWatchlistById(
            @PathVariable Integer watchlistId) {
        return watchlistService.findWatchlistById(watchlistId);
    }


}
