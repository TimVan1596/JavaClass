package com.zhangmin.test;
public class Doctor{
protected String docld;
protected String docName;

public void exam(){
        System.out.println("examed");
        }
public void suggest(){
        System.out.println("gave suggesting");
        }

    public String getDocld() {
        return docld;
    }

    public void setDocld(String docld) {
        this.docld = docld;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}