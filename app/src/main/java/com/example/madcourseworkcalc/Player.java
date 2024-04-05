package com.example.madcourseworkcalc;

public class Player {
    private String name;
    private String number;
    private String time;
    public String date;

    public Player(String name, String number, String time, String date){
        this.name=name;
        this.number=number;
        this.time=time;
        this.date=date;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number=number;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String name){
        this.date=date;
    }





}
