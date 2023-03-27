package com.gym1.entity;

public class Order {

    private int id;
    private int venueId;
    private int venueStateId;
    private int userId;

    public Order(int venueId, int venueStateId, int userId) {
        this.venueId = venueId;
        this.venueStateId = venueStateId;
        this.userId = userId;
    }
    public Order(int id, int venueId, int venueStateId, int userId) {
        this.id = id;
        this.venueId = venueId;
        this.venueStateId = venueStateId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getVenueId() != order.getVenueId()) return false;
        if (getVenueStateId() != order.getVenueStateId()) return false;
        return getUserId() == order.getUserId();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getVenueId();
        result = 31 * result + getVenueStateId();
        result = 31 * result + getUserId();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", venueId=" + venueId +
                ", venueStateId=" + venueStateId +
                ", userId=" + userId +
                '}';
    }
}
