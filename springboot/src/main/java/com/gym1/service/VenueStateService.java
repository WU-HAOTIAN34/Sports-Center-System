package com.gym1.service;


import com.gym1.entity.Venue;
import com.gym1.entity.VenueState;
import com.gym1.mapper.VenueMapper;
import com.gym1.mapper.VenueStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;



@Service
public class VenueStateService {

    @Autowired
    private VenueStateMapper venueStateMapper;

    @Autowired
    private VenueMapper venueMapper;


    public List<VenueState> getPotentialState(int id){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String a = formatter.format(date);
        String now = a.substring(0,11) + "00:00:00";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, 4);
        String max = temp.format(calendar.getTime()) + " 00:00:00";

        List<VenueState> query = venueStateMapper.queryVenueStateByIdDate(id, now, max);
        List<VenueState> query1 = venueStateMapper.queryVenueStateByIdDate2(id, now, max);
        List<VenueState> res = new ArrayList<VenueState>();
        Calendar calendar1 = Calendar.getInstance();

        int num = 0;
        Date begin;
        Date end;
        Date valid;
        for (int i = 0; i < 4; i++){
            for (int j = 9; j < 19; j++){
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, i);

                int year = Integer.parseInt(formatter.format(date).substring(0,4));
                int month = Integer.parseInt(formatter.format(date).substring(5,7));
                int day = Integer.parseInt(formatter.format(date).substring(8,10));

                begin = new Date(year-1900, month-1, day+i, j, 0, 0);
                end = new Date(year-1900, month-1, day+i, j+1, 0, 0);

                if (query1.contains(new VenueState(id, begin, end))){
                    res.add(num, query.get(query1.indexOf(new VenueState(id, begin, end))));
                    calendar1 = Calendar.getInstance();
                    calendar1.add(Calendar.HOUR, 1);
                    valid = calendar1.getTime();
                    if (valid.compareTo(res.get(num).getBegin()) != -1){
                        res.get(num).setOpen(0);
                    }
                }else{
                    res.add(num, new VenueState(id, begin, end, 0, 0));
                }
                num += 1;
            }
        }
        return res;
    }


    public List<VenueState> getAll(int num){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date date = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num+1);
        Date date1 = calendar.getTime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

        String begin = formatter.format(date).substring(0,11) + "00:00:00";
        String end = formatter.format(date1).substring(0,11) + "00:00:00";

        List<VenueState> venueStates = venueStateMapper.queryVenueStateByDate(begin, end);
        List<VenueState> venueStates2 = venueStateMapper.queryVenueStateByDate2(begin, end);
        List<Venue> venues = venueMapper.queryAllVenue();
        List<VenueState> res = new ArrayList<VenueState>();


        Calendar calendar2 = Calendar.getInstance();
        Date now = calendar2.getTime();
        Date date2;
        Date date3;
        int number = 0;
        for (Venue venue: venues){
            for (int i = 9; i < 19; i++){
                int year = Integer.parseInt(formatter.format(date).substring(0,4));
                int month = Integer.parseInt(formatter.format(date).substring(5,7));
                int day = Integer.parseInt(formatter.format(date).substring(8,10));
                date2 = new Date(year-1900, month-1, day, i,0,0);
                date3 = new Date(year-1900, month-1, day, i+1,0,0);

                if (venueStates2.contains(new VenueState(venue.getId(), date2, date3))){
                    int temp = venueStates2.indexOf(new VenueState(venue.getId(), date2, date3));
                    if (now.compareTo(venueStates.get(temp).getEnd()) == 1){
                        venueStates.get(temp).setFree(1);
                    }
                    res.add(number, venueStates.get(temp));
                }else{
                    res.add(number, new VenueState(venue.getId(), date2, date3, 0, 0));
                }
                number += 1;
            }
        }
        return res;
    }


    public int openVenueState(String list, int num){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date date = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num+1);
        Date date1 = calendar.getTime();
        String [] number = list.split(" ");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

        String begin = formatter.format(date).substring(0,11) + "00:00:00";
        String end = formatter.format(date1).substring(0,11) + "00:00:00";

        List<VenueState> venueStates = venueStateMapper.queryVenueStateByDate(begin, end);
        List<VenueState> venueStates2 = venueStateMapper.queryVenueStateByDate2(begin, end);
        List<Venue> venues = venueMapper.queryAllVenue();

        Date date2 = new Date(System.currentTimeMillis());
        Date date3 = new Date(System.currentTimeMillis());
        int res = 1;
        int a = 0;
        int b = 0;
        for (String string : number){
            int position = Integer.parseInt(string);
            a = 0;
            if (position%10 == 0){
                a = position/10-1;
            }else{
                a = (position - position % 10) / 10;
            }
            int venueId = venues.get(a).getId();
            b = 0;
            if (position%10 == 0){
                b = 10;
            }else{
                b = (position%10);
            }

            int year = Integer.parseInt(formatter.format(date).substring(0,4));
            int month = Integer.parseInt(formatter.format(date).substring(5,7));
            int day = Integer.parseInt(formatter.format(date).substring(8,10));
            date2 = new Date(year-1900, month-1, day, b+8,0,0);
            date3 = new Date(year-1900, month-1, day, b+9,0,0);

            if (venueStates2.contains(new VenueState(venueId, date2, date3))){
                int temp = venueStates2.indexOf(new VenueState(venueId, date2, date3));
                int venueStateId = venueStates.get(temp).getId();
                try{
                    res = venueStateMapper.updateVenueStateOpen(venueStateId, 1);
                    if (res == 0){
                        return 0;
                    }
                }catch (Exception e){
                    return -1;
                }
            }else{
                try{
                    res = venueStateMapper.addVenueState(new VenueState(venueId, date2, date3, 1, 1));
                    if (res == 0){
                        return 0;
                    }
                }catch (Exception e){
                    return -1;
                }
            }
        }
        return 1;
    }


    public int closeVenueState(String list, int num){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date date = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num+1);
        Date date1 = calendar.getTime();
        String [] number = list.split(" ");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

        String begin = formatter.format(date).substring(0,11) + "00:00:00";
        String end = formatter.format(date1).substring(0,11) + "00:00:00";

        List<VenueState> venueStates = venueStateMapper.queryVenueStateByDate(begin, end);
        List<VenueState> venueStates2 = venueStateMapper.queryVenueStateByDate2(begin, end);
        List<Venue> venues = venueMapper.queryAllVenue();

        Date date2;
        Date date3;
        int res = 1;
        int a = 0;
        int b = 0;
        for (String string : number) {
            int position = Integer.parseInt(string);
            a = 0;
            if (position%10 == 0){
                a = position/10-1;
            }else{
                a = (position - position % 10) / 10;
            }
            int venueId = venues.get(a).getId();
            b = 0;
            if (position%10 == 0){
                b = 10;
            }else{
                b = (position%10);
            }
            int year = Integer.parseInt(formatter.format(date).substring(0,4));
            int month = Integer.parseInt(formatter.format(date).substring(5,7));
            int day = Integer.parseInt(formatter.format(date).substring(8,10));
            date2 = new Date(year-1900, month-1, day, b+8,0,0);
            date3 = new Date(year-1900, month-1, day, b+9,0,0);
            int temp = venueStates2.indexOf(new VenueState(venueId, date2, date3));
            int venueStateId = venueStates.get(temp).getId();
            try{
                res = venueStateMapper.updateVenueStateOpen(venueStateId, 0);
                if (res == 0){
                    return 0;
                }
            }catch (Exception e){
                return -1;
            }
        }
        return 1;
    }


}
