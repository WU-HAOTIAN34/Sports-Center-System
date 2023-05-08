package com.gym1.mapper;
import com.gym1.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface ItemMapper {

    int addItem(Item item);

    List<Item> queryItemByName(String name);

    List<Item> queryAllItem();

    int updateItemValidById(int id);

    List<Item> queryItemById(int id);

    int updateItemById(String name, String info, double price, String image, int itemId);

    int updateItemById2(String name, String info, double price, int itemId);


}
