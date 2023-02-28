package com.example.kirill;

public class MyMonth {
    private  String month;
    private  double temp;
    private  int days;
    private  boolean like;

    public MyMonth(String month, double temp, int days) {
        this.month = month;
        this.temp = temp;
        this.days = days;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
