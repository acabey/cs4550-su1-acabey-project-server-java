package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Medium;
import com.example.cs4550su1acabeyprojectserverjava.models.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MediumController {

    @Autowired
    MediumService mediumService;

    @PostMapping("/api/watchlist/{watchlistId}/media")
    public Widget watchMedium(@PathVariable String watchlistId, @RequestBody Medium medium) {
        return mediumService.watchMedium(Integer.valueOf(watchlistId), medium);
    }

    @GetMapping("/api/watchlist/{watchlistId}/media")
    public List<Widget> findMediaForWatchlist(
            @PathVariable Integer watchlistId) {
        return mediumService.findMediaForWatchList(watchlistId);
    }

    @DeleteMapping("/api/watchlist/{watchlistId}/media/{mediumId}")
    public Integer unwatchMedium (
            @PathVariable Integer watchlistId,
            @PathVariable Integer mediumid) {
        return mediumService.unwatchMedium(watchlistId, mediumid);
    }
}
