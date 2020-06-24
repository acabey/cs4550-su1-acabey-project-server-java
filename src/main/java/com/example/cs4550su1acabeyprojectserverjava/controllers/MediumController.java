package com.example.cs4550su1acabeyprojectserverjava.controllers;

import com.example.cs4550su1acabeyprojectserverjava.models.Medium;
import com.example.cs4550su1acabeyprojectserverjava.services.MediumService;
import com.example.cs4550su1acabeyprojectserverjava.services.TVDBService;
import com.example.cs4550su1acabeyprojectserverjava.utilities.APIErrorSchema;
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
@CrossOrigin(
        origins = "http://localhost:8080",
        allowedHeaders = "*",
        allowCredentials = "true")
public class MediumController {

    @Autowired
    MediumService mediumService;

    @Autowired
    TVDBService tvdbService;

    @GetMapping("/api/watchlists/{watchlistId}/media")
    public List<Medium> findMediaForWatchlist(
            @PathVariable Integer watchlistId) {
        return mediumService.findMediaForWatchList(watchlistId);
    }

    @PostMapping("/api/watchlists/{watchlistId}/media")
    public Integer watchMedium(
            @PathVariable Integer watchlistId,
            @PathVariable Integer mediumId) {
        return mediumService.unwatchMedium(watchlistId, mediumId);
    }

    @DeleteMapping("/api/watchlists/{watchlistId}/media/{mediumId}")
    public Integer unwatchMedium (
            @PathVariable Integer watchlistId,
            @PathVariable Integer mediumid) {
        return mediumService.unwatchMedium(watchlistId, mediumid);
    }

    @GetMapping("/api/search/{title}")
    public ResponseEntity searchMediaByTitle(
            @PathVariable String title) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tvdbService.searchForSeries(title));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorSchema("Failed to search media"));
        }
    }

    @GetMapping(
            value="/api/media/{mediumId}/poster",
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

        byte[] baos = {};
        //try {
        //    //baos = file.readAllBytes();
        //    baos =
        //} catch (IOException e) {
        //    throw new ResponseStatusException(
        //            HttpStatus.UNPROCESSABLE_ENTITY, "Image processing failed"
        //    );
        //}

        return new ResponseEntity<byte[]>(baos, headers, HttpStatus.OK);
    }
}
