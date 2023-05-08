package com.gym1.mapper;


import com.gym1.entity.Venue;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface VenueMapper {

    int addVenue(Venue venue);

    int updateVenueValidById(int id);

    int updateVenueById(String image, int type, String name, String address, double price, int venueId);

    int updateVenueById2(int type, String name, String address, double price, int venueId);

    List<Venue> queryAllVenue();

    Venue queryVenueById(int venueId);

    List<Venue> queryVenueByName(String name);

    List<Venue> queryVenueByNameType(String type, String name);

    int countVenueTypeNumber(int type);

}
