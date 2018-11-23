package com.smallfangyu.attendance;

public class Attendance {
    private int pno;
    private String name;
    private String entrytime;
    private String state;
    private String time;


    public Attendance(){

    }
    public Attendance(int pno,String name,String entrytime,String state,String time){
        this.pno=pno;
        this.name=name;
        this.entrytime=entrytime;
        this.state=state;
        this.time=time;
    }
    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(String entrytime) {
        this.entrytime = entrytime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
