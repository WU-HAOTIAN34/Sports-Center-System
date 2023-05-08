package com.gym1.entity;


public class Item {

    private int id;
    private String itemName;
    private double price;
    private String info;
    private String image;

    public Item(int id, String itemName, String info, double price, String image){
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.info = info;
        this.image = image;
    }

    public Item(String itemName, double price, String info, String image){
        this.itemName = itemName;
        this.price = price;
        this.info = info;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        if (getId() != item.getId()) return false;
        if (Double.compare(item.getPrice(), getPrice()) != 0) return false;
        if (getItemName() != null ? !getItemName().equals(item.getItemName()) : item.getItemName() != null)
            return false;
        if (getInfo() != null ? !getInfo().equals(item.getInfo()) : item.getInfo() != null) return false;
        return getImage() != null ? getImage().equals(item.getImage()) : item.getImage() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getItemName() != null ? getItemName().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getInfo() != null ? getInfo().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
