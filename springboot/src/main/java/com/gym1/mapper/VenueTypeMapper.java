package com.gym1.mapper;

import com.gym1.entity.VenueType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface VenueTypeMapper {

    int addVenueType(VenueType venueType);

    int deleteVenueType(VenueType venueType);

    int editVenueType(VenueType venueType);

    List<String> queryAllType();

    int queryVenueTypeIdByVenueTypeName(String name);

    List<VenueType> queryAll();
}
