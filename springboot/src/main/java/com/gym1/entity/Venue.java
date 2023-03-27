package com.gym1.entity;

public class Venue {

    private int id;
    private int type;
    private String name;
    private String address;
    private double price;

    public Venue(int type, String name, String address, double price) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.price = price;

    }

    public Venue(int id, int type, String name, String address, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;

        Venue venue = (Venue) o;

        if (getId() != venue.getId()) return false;
        if (getType() != venue.getType()) return false;
        if (Double.compare(venue.getPrice(), getPrice()) != 0) return false;
        if (getName() != null ? !getName().equals(venue.getName()) : venue.getName() != null) return false;
        return getAddress() != null ? getAddress().equals(venue.getAddress()) : venue.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getType();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }
}
