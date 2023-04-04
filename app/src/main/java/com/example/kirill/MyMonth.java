package com.example.kirill;

import java.util.Objects;

public class MyMonth {
    private String month;
    private double temp;
    private int days;
    private boolean like;

    public MyMonth(String month, double temp, int days) {
        this.setMonth(month);
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

    public int hashCode() {
        return Objects.hash(days, temp, month);
    }

    public boolean equals(Object m2) {
        if (m2 == null || !(m2.getClass().equals(this.getClass()))) {
            return false;
        }
        boolean me = month.equals(((MyMonth) m2).getMonth());
        boolean te = temp == ((MyMonth) m2).getTemp();
        boolean da = days == ((MyMonth) m2).getDays();
        return me && da && te;
    }

    public String toString() {
        return  month;
    }
}
