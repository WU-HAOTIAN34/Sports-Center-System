package com.gym1.service;


import com.gym1.entity.Item;
import com.gym1.entity.User;
import com.gym1.entity.Venue;
import com.gym1.mapper.UserMapper;
import com.gym1.mapper.VenueMapper;
import com.gym1.mapper.VenueTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class VenueService {

    @Autowired
    private VenueMapper venueMapper;

    @Autowired
    private VenueTypeMapper venueTypeMapper;

    @Autowired
    private UserMapper userMapper;


    public List<Venue> getAllVenue(int uId, String status){
        List<Venue> venues = venueMapper.queryAllVenue();
        if (status.equals("user")){
            User user = userMapper.queryUserById(uId);
            if (user.getIsMember() == 1){
                for (Venue venue : venues){
                    int price = (int)(venue.getPrice() * 0.75 * 100);
                    venue.setPrice((double)(price / 100));
                }
            }
        }
        return venues;
    }

    public Venue getVenueInfo(int venueId, int uId, String status){
        Venue venue = venueMapper.queryVenueById(venueId);
        if (status.equals("user")){
            User user = userMapper.queryUserById(uId);
            if (user.getIsMember() == 1){
                int price = (int)(venue.getPrice() * 0.75 * 100);
                venue.setPrice((double)(price / 100));
            }
        }
        return venue;
    }

    public int addVenue(String image, String type, String name, String address, double price){
        List<Venue> venues = venueMapper.queryVenueByName(name);
        if (venues.size() != 0){
            return -2;
        }else{
            int typeId = venueTypeMapper.queryVenueTypeIdByVenueTypeName(type);
            Venue venue = new Venue(typeId, name, address, price, image);
            int res = 0;
            try{
                res = venueMapper.addVenue(venue);
                return res;
            }catch (Exception e){
                return -1;
            }
        }
    }

    public int editVenue(String image, String type, String name,
                         String address, double price, int venueId){
        int typeId = venueTypeMapper.queryVenueTypeIdByVenueTypeName(type);
        int res = 0;
        try{
            if (image.equals("data:image/png;base64,")){
                res = venueMapper.updateVenueById2(typeId, name, address, price, venueId);
            }else{
                res = venueMapper.updateVenueById(image, typeId, name, address, price, venueId);
            }
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public int deleteVenue(int id){
        int res = 0;
        try{
            res = venueMapper.updateVenueValidById(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }


}
