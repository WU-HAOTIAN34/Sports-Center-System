package com.gym1.entity;

import java.util.Objects;


public class User {
    private int id;
    private int age;
    private int sex;
    private String username;
    private String password;
    private String phoneNumber;
    private String name;
    private String email;


    public User(String username, String password, String phoneNumber, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
                String phoneNumber, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }
    public int getId() {
        return this.id;
    }
    public int getSex() {
        return this.sex;
    }
    public int getAge() {
        return this.age;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        if (getId() != user.getId()) return false;
        if (getSex() != user.getSex()) return false;
        if (getAge() != user.getAge()) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null)
            return false;
        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;
    }
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getSex();
        result = 31 * result + getAge();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
