package com.gym1.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gym1.util.DefaultProfile;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;
import java.util.Date;


public class User {
    private int id;
    private int age;
    private int sex;
    private String username;
    private String password;
    private String phoneNumber;
    private String name;
    private String email;
    private String image;
    private int admin;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08")
    private Date membership;
    private String card;
    private int isMember;

    public User(int id){
        this.id = id;
    }

    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }


    public User(String username, String password, String phoneNumber, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User(int id, String password, String phoneNumber, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(int age, int sex, String username, String password,
                String phoneNumber, String name, String email) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public User(int id, int age, int sex, String username, String password,
                String phoneNumber, String name, String email, String image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }else{
            this.image = image;
        }
    }

    public User(int id, int age, int sex, String username, String password,
                String phoneNumber, String name, String email, String image,
            String card, Date membership) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }else{
            this.image = image;
        }
        this.card = card;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        this.membership = membership;
        if (membership == null){
            this.isMember = 0;
        }else {
            if (membership.compareTo(date) == -1){
                this.isMember = 0;
            }else{
                this.isMember = 1;
            }
        }
    }

    public User(int id, int age, int sex, String username, String password,
                String phoneNumber, String name, String email, String image, int admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }else{
            this.image = image;
        }
        this.admin = admin;
    }

    public User(int id, int age, int sex, String username, String password,
                String phoneNumber, String name, String email, String image, int admin,
                String card, Date membership) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
        if (image == null){
            this.image = DefaultProfile.getDefaultProfile();
        }else{
            this.image = image;
        }
        this.admin = admin;
        this.card = card;
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        this.membership = membership;
        if (membership == null){
            this.isMember = 0;
        }else {
            if (membership.compareTo(date) == -1){
                this.isMember = 0;
            }else{
                this.isMember = 1;
            }
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getAdmin() {
        return admin;
    }
    public void setAdmin(int admin) {
        this.admin = admin;
    }
    public Date getMembership() {
        return membership;
    }
    public void setMembership(Date membership) {
        this.membership = membership;
    }
    public String getCard() {
        return card;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public int getIsMember() {
        return isMember;
    }
    public void setIsMember(int isMember) {
        this.isMember = isMember;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        if (getId() != user.getId()) return false;
        if (getAge() != user.getAge()) return false;
        if (getSex() != user.getSex()) return false;
        if (getAdmin() != user.getAdmin()) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getImage() != null ? getImage().equals(user.getImage()) : user.getImage() == null;
    }
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getAge();
        result = 31 * result + getSex();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + getAdmin();
        return result;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", sex=" + sex +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", admin=" + admin +
                '}';
    }
}
