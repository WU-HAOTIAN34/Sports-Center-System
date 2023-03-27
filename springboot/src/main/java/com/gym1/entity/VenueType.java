package com.gym1.entity;

public class VenueType {

    private int id;
    private String introduction;
    private String type;

    public VenueType( double price, String introduction, String type){
        this.type = type;
        this.introduction = introduction;
    }
    public VenueType(int id, double price, String introduction, String type){
        this.id = id;
        this.type = type;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueType)) return false;

        VenueType venueType = (VenueType) o;

        if (getId() != venueType.getId()) return false;
        if (getIntroduction() != null ? !getIntroduction().equals(venueType.getIntroduction()) : venueType.getIntroduction() != null)
            return false;
        return getType() != null ? getType().equals(venueType.getType()) : venueType.getType() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getIntroduction() != null ? getIntroduction().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VenueType{" +
                "id=" + id +
                ", introduction='" + introduction + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
