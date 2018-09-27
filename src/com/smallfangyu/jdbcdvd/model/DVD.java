package com.smallfangyu.jdbcdvd.model;


public class DVD {
   private int id;
   private String dvdname;
   private String state;
   
   
   public DVD(int id,String dvdname,String state) {
	   this.id=id;
	   this.dvdname=dvdname;
	   this.state=state;
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
}
