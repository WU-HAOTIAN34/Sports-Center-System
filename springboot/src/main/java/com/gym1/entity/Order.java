package com.gym1.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


public class Order extends Venue{

    private int id;
    private int venueStateId;
    private int userId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date begin;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date end;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date orderTime;
    private int comment;
    private String username;
    private String userName;
    private String phoneNumber;

    public Order( int venueStateId, int userId, Date orderTime) {
        this.venueStateId = venueStateId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.comment = 0;
    }

    public Order( int venueStateId, int userId, Date orderTime, double price) {
        super(price);
        this.venueStateId = venueStateId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.comment = 0;
    }

    public Order(int id, int venueStateId, int userId) {
        this.id = id;
        this.venueStateId = venueStateId;
        this.userId = userId;
    }

    public Order(int id, Date orderTime, Date end, Date begin,
                 String name, String address, double price, int comment) {
        super(name, address, price);
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.orderTime = orderTime;
        this.comment = comment;
    }

    public Order(int id, Date orderTime, Date end, Date begin,
                 String name, String address, double price) {
        super(name, address, price);
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.orderTime = orderTime;
    }

    public Order(int id, Date orderTime, Date end, Date begin,
                 String name, String address, double price,
                 String userN, String username, String phoneNumber) {
        super(name, address, price);
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.orderTime = orderTime;
        this.userName = userN;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    public int getVenueStateId() {
        return venueStateId;
    }
    public void setVenueStateId(int venueStateId) {
        this.venueStateId = venueStateId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getBegin() {
        return begin;
    }
    public void setBegin(Date begin) {
        this.begin = begin;
    }
    public Date getEnd() {
        return end;
    }
    public void setEnd(Date end) {
        this.end = end;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        if (getId() != order.getId()) return false;
        if (getVenueStateId() != order.getVenueStateId()) return false;
        if (getUserId() != order.getUserId()) return false;
        if (getComment() != order.getComment()) return false;
        if (getBegin() != null ? !getBegin().equals(order.getBegin()) : order.getBegin() != null) return false;
        if (getEnd() != null ? !getEnd().equals(order.getEnd()) : order.getEnd() != null) return false;
        return getOrderTime() != null ? getOrderTime().equals(order.getOrderTime()) : order.getOrderTime() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId();
        result = 31 * result + getVenueStateId();
        result = 31 * result + getUserId();
        result = 31 * result + (getBegin() != null ? getBegin().hashCode() : 0);
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        result = 31 * result + (getOrderTime() != null ? getOrderTime().hashCode() : 0);
        result = 31 * result + getComment();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", venueStateId=" + venueStateId +
                ", userId=" + userId +
                ", begin=" + begin +
                ", end=" + end +
                ", orderTime=" + orderTime +
                ", comment=" + comment +
                '}';
    }
}
