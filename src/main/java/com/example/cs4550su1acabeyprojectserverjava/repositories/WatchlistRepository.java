package com.example.cs4550su1acabeyprojectserverjava.repositories;

import com.example.cs4550su1acabeyprojectserverjava.models.Watchlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchlistRepository
        extends CrudRepository<Watchlist, Integer> {

    @Query("SELECT watchlist FROM Watchlist watchlist")
    public List<Watchlist> findAllWatchlists();

    @Query("SELECT watchlist FROM Watchlist watchlist WHERE watchlist.userId=:userId")
    public List<Watchlist> findWatchlistsForOwner(@Param("userId") Integer userId);

    @Query("SELECT watchlist FROM Watchlist watchlist WHERE watchlist.id=:watchlistId")
    public Watchlist findWatchlistById(@Param("watchlistId") Integer watchlistId);

}
