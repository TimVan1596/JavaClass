package com.smallfangyu.data;
import java.io.Serializable;
import java.util.List;

public class DVD implements Serializable{
   private int dvdno;
   private String dvdname;
   private String state;
   private String picture;
   //private String sta;
   private DvdRecy dvdrecy;

    public DVD(){}
   public DVD(int dvdno, String dvdname, String state,String picture) {
	   this.dvdno =dvdno;
	   this.dvdname=dvdname;
	   this.state=state;
	   this.picture=picture;
   }
   
   public int getDvdno() {
	   return dvdno;
   }
   
   public void setDvdno(int dvdno) {
       this.dvdno = dvdno;
   }
   
   public String getDvdname() {
	   return dvdname;
   }
   
   public void setDvdname(String dvdname) {
       this.dvdname=dvdname;
   }
   
   public String getState() {
	   return state;
   }
   
   public void setState(String state) {
       this.state=state;
   }
   
   public  void show()
   {
   	
   	System.out.println(this.dvdno +"\t"+this.dvdname+"\t"+this.state);
   }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

//    public String getSta() {
//        return sta;
//    }
//
//    public void setSta(String sta) {
//        this.sta = sta;
//    }

    public DvdRecy getDvdrecy() {
        return dvdrecy;
    }

    public void setDvdrecy(DvdRecy dvdrecy) {
        this.dvdrecy = dvdrecy;
    }
}
