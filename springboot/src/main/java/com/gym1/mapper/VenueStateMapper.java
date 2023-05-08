package com.gym1.mapper;


import com.gym1.entity.VenueState;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface VenueStateMapper {

    int addVenueState(VenueState venueState);

    int deleteVenueState(VenueState venueState);

    int editVenueState(VenueState venueState);

    List<VenueState> queryAllVenueState();

    List<VenueState> queryVenueStateByIdDate(int id, String now, String max);

    List<VenueState> queryVenueStateByIdDate2(int id, String now, String max);

    VenueState queryVenueStateById(int id);

    int updateVenueStateFree(int id, int free);

    int updateVenueStateOpen(int id, int open);

    List<VenueState> queryVenueStateByDate(String begin, String end);

    List<VenueState> queryVenueStateByDate2(String begin, String end);

    int queryVenueIdByVenueStateId(int id);

}
