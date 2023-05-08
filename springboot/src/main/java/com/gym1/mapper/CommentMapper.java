package com.gym1.mapper;


import com.gym1.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface CommentMapper {

    int addComment(Comment comment);

    List<Comment> queryCommentByVenueId(int venueId);

    List<Comment> queryCommentByUserId(int userId);

    int deleteCommentById(int id);

    List<Comment> queryAllComment();

}
