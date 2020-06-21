package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Medium;
import com.example.cs4550su1acabeyprojectserverjava.services.MediumService;
import com.example.cs4550su1acabeyprojectserverjava.services.TVDBService;
import javassist.bytecode.ByteArray;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MediumController {

    @Autowired
    MediumService mediumService;

    @Autowired
    TVDBService tvdbService;

    @GetMapping("/api/watchlist/{watchlistId}/media")
    public List<Medium> findMediaForWatchlist(
            @PathVariable Integer watchlistId) {
        return mediumService.findMediaForWatchList(watchlistId);
    }

    @PostMapping("/api/watchlist/{watchlistId}/media")
    public Integer watchMedium(
            @PathVariable Integer watchlistId,
            @PathVariable Integer mediumId) {
        return mediumService.unwatchMedium(watchlistId, mediumId);
    }

    @DeleteMapping("/api/watchlist/{watchlistId}/media/{mediumId}")
    public Integer unwatchMedium (
            @PathVariable Integer watchlistId,
            @PathVariable Integer mediumid) {
        return mediumService.unwatchMedium(watchlistId, mediumid);
    }

    @GetMapping(
            value="/api/medium/{mediumId}/poster",
            produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getPosterImage(@PathVariable Integer mediumId) {

        InputStream file = null;

        try {
            file = tvdbService.getPosterImage(mediumId);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Image not found"
            );
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        byte[] baos;
        try {
            baos = file.readAllBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Image processing failed"
            );
        }

        return new ResponseEntity<byte[]>(baos, headers, HttpStatus.OK);
    }
}
