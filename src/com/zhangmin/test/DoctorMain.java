package com.zhangmin.test;

public class DoctorMain{
    private static void justTalk(Doctor doctor){
        doctor.exam();
        doctor.suggest();
    }
    public static void main(String[] args){
        ClincianDoctor clincianDoctor  = new ClincianDoctor();
        DoctorMain.justTalk(clincianDoctor);
        FamilyDoctor familyDoctor = new FamilyDoctor();
        DoctorMain.justTalk(familyDoctor);

    }
}
