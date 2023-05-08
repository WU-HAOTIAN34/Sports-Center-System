package com.gym1.controller;


import com.gym1.entity.Comment;
import com.gym1.service.CommentService;
import com.gym1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/makeComment/{orderId}")
    public Map<String, Object> makeComment(HttpServletRequest request,@PathVariable int orderId,
                                           @RequestBody Map map){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        String info = map.get("comment").toString();
        int res = commentService.makeComment(uId, orderId, info);
        if (res == -1){
            reMap.put("code", 1001);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 1002);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 1003);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getVenueComment/{venueId}")
    public Map<String, Object> getVenueComment(HttpServletRequest request, @PathVariable int venueId){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));

        List<Comment> res = commentService.getVenueComment(venueId, uId);
        if (res.size() != 0){
            reMap.put("code", 1004);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 1005);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/getUserComment")
    public Map<String, Object> getUserComment(HttpServletRequest request){
        Map<String, Object> reMap = new HashMap<>();
        int uId = Integer.parseInt(JwtUtil.getMemberIdByJwtToken(request));
        List<Comment> res = commentService.getUserComment(uId);
        if (res.size() != 0){
            reMap.put("code", 1006);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 1007);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @PostMapping("/deleteComment/{commentId}")
    public Map<String, Object> deleteComment(@PathVariable int commentId){
        Map<String, Object> reMap = new HashMap<>();
        int res = commentService.deleteComment(commentId);
        if (res == -1){
            reMap.put("code", 1008);
            reMap.put("msg", "Error!");
            reMap.put("data", res);
        }else if (res == 0){
            reMap.put("code", 1009);
            reMap.put("msg", "Failure!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 1010);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }
        return reMap;
    }


    @GetMapping("/admin/getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> reMap = new HashMap<>();
        List<Comment> res = commentService.getAll();
        if (res.size() != 0){
            reMap.put("code", 1011);
            reMap.put("msg", "Success!");
            reMap.put("data", res);
        }else{
            reMap.put("code", 1012);
            reMap.put("msg", "There are no comments!");
            reMap.put("data", res);
        }
        return reMap;
    }


}

