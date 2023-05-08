package com.gym1.service;


import com.gym1.entity.Item;
import com.gym1.entity.User;
import com.gym1.mapper.ItemMapper;
import com.gym1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserMapper userMapper;


    public int addItem(String name, String info, double price, String image){
        List<Item> temp = itemMapper.queryItemByName(name);
        if (temp.size() != 0){
            return -2;
        }else{
            Item item = new Item(name, price, info, image);
            int res = 0;
            try{
                res = itemMapper.addItem(item);
                return res;
            }catch (Exception e){
                return -1;
            }
        }
    }

    public List<Item> getAllItem(int uId, String status){
        List<Item> items = itemMapper.queryAllItem();
        if (status.equals("user")){
            User user = userMapper.queryUserById(uId);
            if (user.getIsMember() == 1){
                for (Item item : items){
                    int price = (int)(item.getPrice() * 0.75 * 100);
                    item.setPrice((double)(price / 100));
                }
            }
        }
        return items;
    }

    public int deleteItem(int id){
        int res = 0;
        try{
            res = itemMapper.updateItemValidById(id);
            return res;
        }catch (Exception e){
            return -1;
        }
    }

    public Item getItemInfo(int id, int uId, String status){
        Item item = itemMapper.queryItemById(id).get(0);
        if (status.equals("user")){
            User user = userMapper.queryUserById(uId);
            if (user.getIsMember() == 1){
                int price = (int)(item.getPrice() * 0.75 * 100);
                item.setPrice((double)(price / 100));
            }
        }
        return item;
    }

    public int editItem(String name, String info, double price, String image, int itemId){
        int res = 0;
        try{
            if (image.equals("data:image/png;base64,")){
                res = itemMapper.updateItemById2(name, info, price, itemId);
            }else{
                res = itemMapper.updateItemById(name, info, price, image, itemId);
            }
            return res;
        }catch (Exception e){
            return -1;
        }
    }

}
