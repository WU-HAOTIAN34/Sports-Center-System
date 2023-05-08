package com.gym1.entity;
import java.util.Arrays;



public class VenueType {

    private int id;
    private String introduction;
    private String typeName;

    public VenueType(){

    }

    public VenueType(int id, String typeName){
        this.id = id;
        this.typeName = typeName;
    }

    public VenueType(String introduction, String typeName){
        this.introduction = introduction;
        this.typeName = typeName;
    }
    public VenueType(int id, String introduction, String type){
        this.id = id;
        this.typeName = type;
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setTypeName(String type) {
        this.typeName = type;
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
        return getTypeName() != null ? getTypeName().equals(venueType.getTypeName()) : venueType.getTypeName() == null;
    }
    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getIntroduction() != null ? getIntroduction().hashCode() : 0);
        result = 31 * result + (getTypeName() != null ? getTypeName().hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "VenueType{" +
                "id=" + id +
                ", introduction='" + introduction + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
