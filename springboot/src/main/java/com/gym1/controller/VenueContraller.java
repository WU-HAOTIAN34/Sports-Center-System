package com.gym1.controller;


import com.gym1.entity.Venue;
import com.gym1.service.UserService;
import com.gym1.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/venue")
public class VenueContraller {

    @Autowired
    private VenueService venueService;

    @GetMapping("/getAll")
    public List<Venue> getAll(){
        System.out.println(venueService.getAllVenue());
        return venueService.getAllVenue();
    }


}
