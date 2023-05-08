package com.gym1.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gym1.util.DefaultProfile;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


public class ItemComment {

    private int id;
    private int userId;
    private int orderId;
    private int itemId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date commentTime;
    private String username;
    private String info;
    private String image;
    private String itemImage;
    private String itemName;

    public ItemComment( Date commentTime, String info, int userId, int orderId, int itemId) {
        this.userId = userId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.commentTime = commentTime;
        this.info = info;
    }

    public ItemComment(int id, Date commentTime, String info, String username,
                   String image, int userId){
        this.id = id;
        this.commentTime = commentTime;
        this.info = info;
        this.username = username;
        this.image = image;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }
        this.userId = userId;
    }

    public ItemComment(int id, Date commentTime, String info, String username,
                   String image, String name, String venueImage){
        this.id = id;
        this.commentTime = commentTime;
        this.info = info;
        this.username = username;
        this.image = image;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }
        this.itemName = name;
        this.itemImage = venueImage;
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
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public Date getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getItemImage() {
        return itemImage;
    }
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemComment)) return false;
        ItemComment that = (ItemComment) o;
        if (getId() != that.getId()) return false;
        if (getUserId() != that.getUserId()) return false;
        if (getOrderId() != that.getOrderId()) return false;
        if (getItemId() != that.getItemId()) return false;
        if (getCommentTime() != null ? !getCommentTime().equals(that.getCommentTime()) : that.getCommentTime() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getInfo() != null ? !getInfo().equals(that.getInfo()) : that.getInfo() != null) return false;
        if (getImage() != null ? !getImage().equals(that.getImage()) : that.getImage() != null) return false;
        if (getItemImage() != null ? !getItemImage().equals(that.getItemImage()) : that.getItemImage() != null)
            return false;
        return getItemName() != null ? getItemName().equals(that.getItemName()) : that.getItemName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUserId();
        result = 31 * result + getOrderId();
        result = 31 * result + getItemId();
        result = 31 * result + (getCommentTime() != null ? getCommentTime().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getInfo() != null ? getInfo().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getItemImage() != null ? getItemImage().hashCode() : 0);
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemComment{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", commentTime=" + commentTime +
                ", username='" + username + '\'' +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
