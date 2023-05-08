package com.gym1.mapper;


import com.gym1.entity.Comment;
import com.gym1.entity.ItemComment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;



@Mapper
public interface ItemCommentMapper {

    int addItemComment(ItemComment itemComment);

    List<ItemComment> queryItemCommentByItemId(int id);

    List<ItemComment> queryItemCommentByUserId(int id);

    int deleteItemCommentById(int id);

    List<ItemComment> queryAllItemComment();

}
