package com.gym1.controller;


import com.gym1.entity.Item;
import com.gym1.service.ItemService;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/addItem")
    public Map<String, Object> addItem(@RequestBody Map<String,String> map) throws IOException{
        Map<String, Object> reMap = new HashMap<>();
        String image = map.get("upload");
        String name = map.get("name");
        String info = map.get("info");
        double price = Double.parseDouble(map.get("price"));
        int res = itemService.addItem(name, info, price, "data:image/png;base64,"+image);
        if (res == -2){
            reMap.put("code", 3001);
            reMap.put("msg", "The item has existed!");
            reMap.put("data", res);
        }else if(res == -1){
            reMap.put("code", 3002);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 3003);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 3004);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/getAllItem")
    public Map<String, Object> getAllItem(HttpServletRequest request, @RequestBody Map map){
        String status = map.get("status").toString();
        String jwtToken = request.getHeader("token");
        int uId = 1;
        if (jwtToken != null){
            uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        }
        Map<String, Object> reMap = new HashMap<>();
        List<Item> res = itemService.getAllItem(uId, status);
        if (res.size() == 0){
            reMap.put("code", 3005);
            reMap.put("msg", "There are no items!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 3006);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/getItemInfo/{itemId}")
    public Map<String, Object> getItemInfo(HttpServletRequest request, @RequestBody Map map,
                                           @PathVariable int itemId){
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String status = map.get("status").toString();
        Map<String, Object> reMap = new HashMap<>();
        Item item = itemService.getItemInfo(itemId, uId, status);
        reMap.put("code", 3004);
        reMap.put("msg", "Success!");
        reMap.put("data", item);
        return reMap;
    }


    @PostMapping("/editItem/{itemId}")
    public Map<String, Object> editItem(@RequestBody Map<String,String> map,
                                        @PathVariable int itemId) throws IOException{
        String image = map.get("upload");
        String name = map.get("name");
        String info = map.get("info");
        double price = Double.parseDouble(map.get("price"));
        int res = itemService.editItem(name, info, price, "data:image/png;base64,"+image, itemId);
        Map<String, Object> reMap = new HashMap<>();
        if (res == -1){
            reMap.put("code", 3008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 3009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 3010);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteItem/{itemId}")
    public Map<String, Object> deleteItem(@PathVariable int itemId){
        Map<String, Object> reMap = new HashMap<>();
        int res = itemService.deleteItem(itemId);
        if (res == -1){
            reMap.put("code", 3011);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if(res == 0){
            reMap.put("code", 3012);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 3013);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }

}
