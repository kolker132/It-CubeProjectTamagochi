package com.example.kirill;

public class User {
    private String name;
    private String lastname;
    private int school;

    public User(String name, String lastname, int school) {
        this.name = name;
        this.lastname = lastname;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return lastname + " " + name;
    }
}
