package com.gym1.controller;


import com.gym1.entity.Venue;
import com.gym1.service.VenueService;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/venue")
public class VenueController {

    @Autowired
    private VenueService venueService;


    @PostMapping("/getVenueInfo/{venueId}")
    public Map<String, Object> getVenueInfo(HttpServletRequest request, @RequestBody Map map, @PathVariable int venueId){
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String status = map.get("status").toString();
        Map<String, Object> reMap = new HashMap<>();
        Venue res = venueService.getVenueInfo(venueId, uId, status);
        reMap.put("code", 8001);
        reMap.put("msg", "Success!");
        reMap.put("data", res);
        return reMap;
    }


    @PostMapping("/getAll")
    public Map<String, Object> getAll(HttpServletRequest request, @RequestBody Map map){
        int uId = 1;
        String status = map.get("status").toString();
        System.out.println(status);
        String jwtToken = request.getHeader("token");
        if (jwtToken != null){
            uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        }
        Map<String, Object> reMap = new HashMap<>();
        List<Venue> res = venueService.getAllVenue( uId, status);
        if (res.size() == 0){
            reMap.put("code", 8002);
            reMap.put("msg", "There are no venues!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 8003);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/addVenue")
    public Map<String, Object> addVenue(@RequestBody Map<String,String> map) throws IOException {
        Map<String, Object> reMap = new HashMap<>();
        String image = map.get("upload");
        String type = map.get("type");
        String name = map.get("name");
        String address = map.get("address");
        double price = Double.parseDouble(map.get("price"));
        int res = venueService.addVenue("data:image/png;base64,"+image, type, name, address, price);
        if (res == -2){
            reMap.put("code", 8004);
            reMap.put("msg", "The item has existed!");
            reMap.put("data", res);
        }else if(res == -1){
            reMap.put("code", 8005);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 8006);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 8007);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/editVenue/{venueId}")
    public Map<String, Object> editItem(@RequestBody Map<String,String> map, @PathVariable int venueId) throws IOException{
        String image = map.get("upload");
        String type = map.get("type");
        String name = map.get("name");
        String address = map.get("address");
        double price = Double.parseDouble(map.get("price"));
        int res = venueService.editVenue("data:image/png;base64,"+image, type, name, address, price, venueId);
        Map<String, Object> reMap = new HashMap<>();
        if (res == -1){
            reMap.put("code", 8008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 8009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 8010);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteVenue/{venueId}")
    public Map<String, Object> deleteItem(@PathVariable int venueId){
        Map<String, Object> reMap = new HashMap<>();
        int res = venueService.deleteVenue(venueId);
        if (res == -1){
            reMap.put("code", 8011);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 8012);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 8013);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }

}
