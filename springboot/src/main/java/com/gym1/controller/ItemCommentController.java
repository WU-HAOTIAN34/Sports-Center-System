package com.gym1.controller;


import com.gym1.entity.Comment;
import com.gym1.entity.ItemComment;
import com.gym1.service.ItemCommentService;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/itemComment")
public class ItemCommentController {

    @Autowired
    ItemCommentService itemCommentService;


    @PostMapping("/makeComment/{itemOrderId}")
    public Map<String, Object> makeComment(HttpServletRequest request, @PathVariable int itemOrderId,
                                           @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String info = map.get("comment").toString();
        int res = itemCommentService.makeItemComment(uId, itemOrderId, info);
        if (res == -1){
            reMap.put("code", 2001);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 2002);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 2003);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getItemComment/{itemId}")
    public Map<String, Object> getItemComment(HttpServletRequest request, @PathVariable int itemId){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<ItemComment> res = itemCommentService.getItemComment(itemId, uId);
        if (res.size() != 0){
            reMap.put("code", 2004);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 2005);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getUserComment")
    public Map<String, Object> getUserComment(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<ItemComment> res = itemCommentService.getUserComment(uId);
        if (res.size() != 0){
            reMap.put("code", 2006);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 2007);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteComment/{commentId}")
    public Map<String, Object> deleteComment(@PathVariable int commentId){
        Map<String, Object> reMap = new HashMap<>();
        int res = itemCommentService.deleteComment(commentId);
        if (res == -1){
            reMap.put("code", 2008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 2009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 2010);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }

    @GetMapping("/admin/getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> reMap = new HashMap<>();
        List<ItemComment> res = itemCommentService.getAll();
        if (res.size() != 0){
            reMap.put("code", 2011);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 2012);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }

}
