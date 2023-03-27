package com.gym1.service;

import com.gym1.entity.Venue;
import com.gym1.mapper.VenueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueMapper venueMapper;

    public List<Venue> getAllVenue(){

        return venueMapper.queryAllVenue();

    }




}
