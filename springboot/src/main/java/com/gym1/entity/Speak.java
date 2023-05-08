package com.gym1.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gym1.util.DefaultProfile;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


public class Speak {

    private int id;
    private String image;
    private int userId;
    private String info;
    private String username;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date time;

    public Speak(int id, Date time, String info, String username, String image, int userId){
        this.id = id;
        this.time = time;
        this.userId = userId;
        this.info = info;
        this.username = username;
        this.image = image;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }
    }

    public Speak(Date time, int userId, String info){
        this.time = time;
        this.userId = userId;
        this.info = info;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speak)) return false;
        Speak speak = (Speak) o;
        if (getId() != speak.getId()) return false;
        if (getUserId() != speak.getUserId()) return false;
        if (getImage() != null ? !getImage().equals(speak.getImage()) : speak.getImage() != null) return false;
        if (getInfo() != null ? !getInfo().equals(speak.getInfo()) : speak.getInfo() != null) return false;
        if (getUsername() != null ? !getUsername().equals(speak.getUsername()) : speak.getUsername() != null)
            return false;
        return getTime() != null ? getTime().equals(speak.getTime()) : speak.getTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + (getInfo() != null ? getInfo().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Speak{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", userId=" + userId +
                ", info='" + info + '\'' +
                ", username='" + username + '\'' +
                ", time=" + time +
                '}';
    }
}
