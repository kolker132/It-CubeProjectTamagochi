package com.example.kirill;

public class Main2 {


    public static int f1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(f1(46));

    }
}

