package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import com.example.cs4550su1acabeyprojectserverjava.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WatchlistController {

    @Autowired
    WatchlistService watchlistService;

    @PostMapping("/api/watchlist")
    public Integer createWatchlist(
            @PathVariable Integer watchlistId,
            @RequestBody Watchlist watchlist) {
        return watchlistService.createWatchlist(watchlist);
    }

    @DeleteMapping("/api/watchlist/{watchlistId}")
    public Integer deleteWatchlist(
            @PathVariable Integer watchlistId) {
        return watchlistService.deleteWatchlist(watchlistId);
    }

    /**
     * For moderator / administrator: finds all watchlists
     * For user: finds public watchlists + their own watchlists
     * For anonymous: finds public watchlists
     * @return
     */
    @GetMapping("/api/watchlists")
    public List<Widget> findAllWatchlists() {
        return watchlistService.findAllWatchlists();
    }

    @GetMapping("/api/watchlists/{watchlistId}")
    public Widget findWatchlistById(
            @PathVariable String widgetId) {
        return watchlistService.findWatchlistById(watchlistId);
    }


}
