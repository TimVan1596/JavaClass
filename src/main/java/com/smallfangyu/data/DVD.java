package com.smallfangyu.data;
import java.io.Serializable;

public class DVD implements Serializable{
   private int id;
   private String dvdname;
   private String state;
    private String picture;

    public DVD(){}
   public DVD(int id, String dvdname, String state,String picture) {
	   this.id=id;
	   this.dvdname=dvdname;
	   this.state=state;
	   this.picture=picture;
   }
   
   public int getId() {
	   return id;
   }
   
   public void setId(int id) {
       this.id=id;
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
   	
   	System.out.println(this.id+"\t"+this.dvdname+"\t"+this.state);
   }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
