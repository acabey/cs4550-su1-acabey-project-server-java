package com.example.cs4550su1acabeyprojectserverjava.services;

import com.example.cs4550su1acabeyprojectserverjava.models.Medium;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediumService {

        public List<Medium> findMediaForWatchList(Integer watchlistId) {
                return new ArrayList<Medium>();
        }

        public Integer watchMedium(Integer watchlistId, Integer mediumId) {
                return 0;
        }

        public Integer unwatchMedium(Integer watchlistId, Integer mediumId) {
                return 0;
        }

}
