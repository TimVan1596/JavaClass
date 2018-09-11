package com.timvanx.homework;

public class DoctorManage {
    public static Doctor[] docs=new Doctor[5];
    public static void initDoctors() {
        docs[0] = new Doctor();
        docs[1] = new Doctor();
        docs[2] = new Doctor();
        docs[3] = new FamilyDoctor();
        docs[4] = new FamilyDoctor();

    }
    public static void main(String[] args) {
        DoctorManage.initDoctors();
        System.out.println(DoctorManage.docs.length);

    }
}
//-------------------------------------------------
class Doctor {

}
//-------------------------------------------------
class FamilyDoctor extends Doctor{

}