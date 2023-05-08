package com.gym1.service;


import com.gym1.mapper.VenueMapper;
import com.gym1.mapper.VenueTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class VenueTypeService {

    @Autowired
    private VenueTypeMapper venueTypeMapper;

    public List<String> getAll(){
        return venueTypeMapper.queryAllType();
    }

}
