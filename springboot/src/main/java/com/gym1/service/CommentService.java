package com.gym1.service;


import com.gym1.entity.Comment;
import com.gym1.mapper.CommentMapper;
import com.gym1.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;



@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private OrderMapper orderMapper;

    public int makeComment(int userId, int orderId, String info){
        int venueId = orderMapper.queryVenueIdByOrderId(orderId);
        Date date = new Date(System.currentTimeMillis());
        Comment comment = new Comment(userId, orderId, venueId, date, info);
        int res = 0;
        try{
            res = orderMapper.editCommentState(orderId, 1);
            if (res == 0){
                return res;
            }else{
                try{
                    res = commentMapper.addComment(comment);
                    return res;
                }catch (Exception e1){
                    orderMapper.editCommentState(orderId, 0);
                    return -1;
                }
            }
        }catch (Exception e){
            res = -1;
            return res;
        }
    }

    public List<Comment> getVenueComment(int venueId, int id){
        List<Comment> comments = commentMapper.queryCommentByVenueId(venueId);
        for (Comment comment : comments) {
            if (comment.getUserId() != id) {
                comment.setUserId(0);
            } else {
                comment.setUserId(1);
            }
        }
        return comments;
    }

    public List<Comment> getUserComment(int userId){
        return commentMapper.queryCommentByUserId(userId);
    }

    public int deleteComment(int id){
        int res = 0;
        try{
            res = commentMapper.deleteCommentById(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public List<Comment> getAll(){
        return commentMapper.queryAllComment();
    }

}
