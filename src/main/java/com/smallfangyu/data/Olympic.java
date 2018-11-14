package com.smallfangyu.data;

public class Olympic {
    private String country;
    private String photo;
    private int goldMedal;
    private int silverMedal;
    private int bronzeMedal;
    private int total;

    public Olympic(){}

    public Olympic(String country,String photo,int goldMedal,int silverMedal,int bronzeMedal,int total){
       this.country=country;
       this.photo=photo;
       this.goldMedal=goldMedal;
       this.silverMedal=silverMedal;
       this.bronzeMedal=bronzeMedal;
       this.total=total;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getGoldMedal() {
        return goldMedal;
    }

    public void setGoldMedal(int goldMedal) {
        this.goldMedal = goldMedal;
    }

    public int getSilverMedal() {
        return silverMedal;
    }

    public void setSilverMedal(int silverMedal) {
        this.silverMedal = silverMedal;
    }

    public int getBronzeMedal() {
        return bronzeMedal;
    }

    public void setBronzeMedal(int bronzeMedal) {
        this.bronzeMedal = bronzeMedal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
