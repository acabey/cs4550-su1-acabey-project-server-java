package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import com.example.cs4550su1acabeyprojectserverjava.services.WatchlistService;
import com.example.cs4550su1acabeyprojectserverjava.utilities.APIErrorSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = "http://localhost:8080",
        allowedHeaders = "*",
        allowCredentials = "true")
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
    public ResponseEntity updateWatchlist(
            @PathVariable Integer watchlistId,
            @RequestBody Watchlist newWatchlist) {
        Watchlist updated = watchlistService.updateWatchlist(watchlistId, newWatchlist);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updated);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorSchema("Failed to update Watchlist"));
        }
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
