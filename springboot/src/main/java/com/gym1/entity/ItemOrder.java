package com.gym1.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


public class ItemOrder {

    private int id;
    private int userId;
    private int itemId;
    private int number;
    private double price;
    private String username;
    private String name;
    private String phoneNum;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date orderTime;
    private int comment;
    private String image;
    private String itemName;

    public ItemOrder(int id, Date orderTime, int number, double price, String phoneNum,
                     String name, String itemName, String image) {
        this.id = id;
        this.orderTime = orderTime;
        this.number = number;
        this.price = price;
        this.phoneNum = phoneNum;
        this.name = name;
        this.itemName = itemName;
        this.image = image;

    }


    public ItemOrder(int id, Date orderTime, int number, double price, String phoneNum,
                     String name, String itemName, String image, String username) {
        this.id = id;
        this.orderTime = orderTime;
        this.number = number;
        this.price = price;
        this.phoneNum = phoneNum;
        this.name = name;
        this.itemName = itemName;
        this.image = image;
        this.username = username;
    }

    public ItemOrder(int id, int userId, int itemId, int number, double price,
                     String username, String name, String phoneNum, Date orderTime) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.number = number;
        this.price = price;
        this.username = username;
        this.name = name;
        this.phoneNum = phoneNum;
        this.orderTime = orderTime;
    }

    public ItemOrder( int userId, int itemId, int number, double price,
                     String username, String name, String phoneNum, Date orderTime) {
        this.userId = userId;
        this.itemId = itemId;
        this.number = number;
        this.price = price;
        this.username = username;
        this.name = name;
        this.phoneNum = phoneNum;
        this.orderTime = orderTime;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public Date getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
    public int getComment() {
        return comment;
    }
    public void setComment(int comment) {
        this.comment = comment;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemOrder)) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        if (getId() != itemOrder.getId()) return false;
        if (getUserId() != itemOrder.getUserId()) return false;
        if (getItemId() != itemOrder.getItemId()) return false;
        if (getNumber() != itemOrder.getNumber()) return false;
        if (Double.compare(itemOrder.getPrice(), getPrice()) != 0) return false;
        if (getComment() != itemOrder.getComment()) return false;
        if (getUsername() != null ? !getUsername().equals(itemOrder.getUsername()) : itemOrder.getUsername() != null)
            return false;
        if (getName() != null ? !getName().equals(itemOrder.getName()) : itemOrder.getName() != null) return false;
        if (getPhoneNum() != null ? !getPhoneNum().equals(itemOrder.getPhoneNum()) : itemOrder.getPhoneNum() != null)
            return false;
        if (getOrderTime() != null ? !getOrderTime().equals(itemOrder.getOrderTime()) : itemOrder.getOrderTime() != null)
            return false;
        if (getImage() != null ? !getImage().equals(itemOrder.getImage()) : itemOrder.getImage() != null) return false;
        return getItemName() != null ? getItemName().equals(itemOrder.getItemName()) : itemOrder.getItemName() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getUserId();
        result = 31 * result + getItemId();
        result = 31 * result + getNumber();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhoneNum() != null ? getPhoneNum().hashCode() : 0);
        result = 31 * result + (getOrderTime() != null ? getOrderTime().hashCode() : 0);
        result = 31 * result + getComment();
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", number=" + number +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", orderTime=" + orderTime +
                ", comment=" + comment +
                ", image='" + image + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
