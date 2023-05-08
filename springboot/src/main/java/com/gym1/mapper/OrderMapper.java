package com.gym1.mapper;


import com.gym1.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface OrderMapper {

    int addOrder(Order order);

    int editOrder(Order order);

    List<Order> queryOrderByUserId(int id);

    List<Order> queryAllOrder();

    int queryVenueIdByOrderId(int orderId);

    int editCommentState(int orderId, int state);

    List<Order> queryOrderByUserIdComment(int id);

    int deleteOrder(int id);

    int queryVenueStateIdByOrderId(int id);

    Order queryOrderById(int id);

    int queryOrderIdByOrder(Order order);

    int countNumberByVenueId(int id, String day);

}
