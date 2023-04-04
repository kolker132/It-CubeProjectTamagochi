package com.example.kirill;

public class Main {

    public static void f1(int n) throws Exception {
        System.out.println(n);
        if (n < 4) {
            n++;
            f1(n);
        }
    }

    public static void main(String[] args) throws Exception {
        f1(0);
        f1(2);
        f1(0);
        f1(2);

    }
}
