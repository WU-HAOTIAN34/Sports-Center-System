package com.gym1.controller;


import com.gym1.entity.VenueState;
import com.gym1.service.VenueStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/venueState")
public class VenueStateController {

    @Autowired
    private VenueStateService venueStateService;


    @GetMapping("/admin/getAllTime/{num}")
    public Map<String, Object> getAllTime(@PathVariable int num){
        Map<String, Object> reMap = new HashMap<>();
        List<VenueState> res = venueStateService.getAll(num);
        reMap.put("code", 9001);
        reMap.put("msg", "Success!");
        reMap.put("data", res);
        return reMap;
    }


    @PostMapping("/admin/openVenue/{num}")
    public Map<String, Object> openVenue(@PathVariable int num, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        String list = map.get("list").toString();
        int res = venueStateService.openVenueState(list, num);
        if(res == 0){
            reMap.put("code", 9002);
            reMap.put("message", "Failure!");
            reMap.put("data", res);
        }else if(res == -1){
            reMap.put("code", 9003);
            reMap.put("message", "Error!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 9004);
            reMap.put("message", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/admin/closeVenue/{num}")
    public Map<String, Object> closeVenue(@PathVariable int num, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        String list = map.get("list").toString();
        int res = venueStateService.closeVenueState(list, num);
        if(res == 0){
            reMap.put("code", 9005);
            reMap.put("message", "Failure!");
            reMap.put("data", res);
        }else if(res == -1){
            reMap.put("code", 9006);
            reMap.put("message", "Error!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 9007);
            reMap.put("message", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/admin/getDay/{num}")
    public Map<String, Object> getDay(@PathVariable int num){
        Map<String, Object> reMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date date = calendar.getTime();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        String res = formatter.format(date).substring(0,11);
        reMap.put("code", 9008);
        reMap.put("msg", "Success!");
        reMap.put("data", res);
        return reMap;
    }


    @GetMapping("/getTime/{id}")
    public List<VenueState> getTime(@PathVariable int id){
        return venueStateService.getPotentialState(id);
    }

}
