package com.gym1.mapper;
import com.gym1.entity.ItemOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;



@Mapper
public interface ItemOrderMapper {

    int addItemOrder(ItemOrder itemOrder);

    int queryItemIdByOrderId(int id);

    int editItemOrderCommentState(int orderId, int state);

    List<ItemOrder> queryItemOrderByUserId(int id);

    List<ItemOrder> queryItemOrderByUserIdComment(int id);

    int deleteItemOrder(int id);

    List<ItemOrder> queryAllItemOrder();

    ItemOrder queryItemOrderByItemOrderId(int id);

    int queryItemOrderIdByItemOrder(int userId, int itemId, int number, double price,
                                    String username, String name, String phoneNumber, String date);

    int countNumberByItemId(int id, String day);

}
