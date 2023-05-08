package com.gym1.controller;


import com.gym1.entity.Speak;
import com.gym1.service.SpeakService;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/speak")
public class SpeakController {

    @Autowired
    SpeakService speakService;

    @PostMapping("/sendSpeak")
    public Map<String, Object> sendSpeak(HttpServletRequest request, @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String info = map.get("speak").toString();
        int res = speakService.sendSpeak(uId, info);
        if (res == -1){
            reMap.put("code", 6001);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 6002);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 6003);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getAllSpeak")
    public Map<String, Object> sendSpeak(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<Speak> res = speakService.getAllSpeak(uId);
        if (res.size() == 0){
            reMap.put("code", 6004);
            reMap.put("msg", "There are no speak!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 6005);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/admin/deleteSpeak/{id}")
    public Map<String, Object> deleteSpeak(@PathVariable int id){
        Map<String, Object> reMap = new HashMap<>();
        int res = speakService.deleteSpeak(id);
        if (res == -1){
            reMap.put("code", 6006);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 6007);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 6008);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }

}
