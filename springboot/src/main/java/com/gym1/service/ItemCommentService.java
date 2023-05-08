package com.gym1.service;


import com.gym1.entity.Comment;
import com.gym1.entity.ItemComment;
import com.gym1.mapper.ItemCommentMapper;
import com.gym1.mapper.ItemOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;



@Service
public class ItemCommentService {

    @Autowired
    ItemCommentMapper itemCommentMapper;

    @Autowired
    ItemOrderMapper itemOrderMapper;

    public int makeItemComment(int userId, int itemOrderId, String info){

        int itemId = itemOrderMapper.queryItemIdByOrderId(itemOrderId);
        Date date = new Date(System.currentTimeMillis());
        ItemComment itemComment = new ItemComment(date, info, userId, itemOrderId, itemId);
        int res = 0;
        try{
            res = itemOrderMapper.editItemOrderCommentState(itemOrderId, 1);
            if (res == 0){
                return res;
            }else{
                try{
                    res = itemCommentMapper.addItemComment(itemComment);
                    return res;
                }catch (Exception e1){
                    itemOrderMapper.editItemOrderCommentState(itemOrderId, 0);
                    return -1;
                }
            }
        }catch (Exception e){
            res = -1;
            return res;
        }
    }

    public List<ItemComment> getItemComment(int itemId, int id){
        List<ItemComment> comments = itemCommentMapper.queryItemCommentByItemId(itemId);
        for (ItemComment comment : comments) {
            if (comment.getUserId() != id) {
                comment.setUserId(0);
            } else {
                comment.setUserId(1);
            }
        }
        return comments;
    }

    public List<ItemComment> getUserComment(int userId){
        return itemCommentMapper.queryItemCommentByUserId(userId);
    }

    public int deleteComment(int id){
        int res = 0;
        try{
            res = itemCommentMapper.deleteItemCommentById(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public List<ItemComment> getAll(){
        return itemCommentMapper.queryAllItemComment();
    }

}
