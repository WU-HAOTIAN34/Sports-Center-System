package com.gym1.entity;


public class Venue extends VenueType{

    private int id;
    private int typeId;
    private String name;
    private String address;
    private double price;
    private String image;

    public Venue(){}

    public Venue(double price){
        this.price = price;
    }

    public Venue(int type, String name, String address, double price) {
        this.typeId = type;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public Venue(String name, String address, double price) {
        super();
        this.name = name;
        this.address = address;
        this.price = price;

    }

    public Venue(int id, int type, String name, String address, double price) {
        this.id = id;
        this.typeId = type;
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public Venue(int typeId, String name, String address, double price, String image) {
        super();
        this.typeId = typeId;
        this.name = name;
        this.address = address;
        this.price = price;
        this.image = image;
    }

    public Venue(int id, String typeName, String introduction,
                 String name, String address, double price, String image) {
        super(introduction, typeName);
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.image = image;
    }

    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue)) return false;
        if (!super.equals(o)) return false;
        Venue venue = (Venue) o;
        if (getId() != venue.getId()) return false;
        if (getTypeId() != venue.getTypeId()) return false;
        if (Double.compare(venue.getPrice(), getPrice()) != 0) return false;
        if (getName() != null ? !getName().equals(venue.getName()) : venue.getName() != null) return false;
        if (getAddress() != null ? !getAddress().equals(venue.getAddress()) : venue.getAddress() != null) return false;
        return getImage() != null ? getImage().equals(venue.getImage()) : venue.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + getId();
        result = 31 * result + getTypeId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

}
