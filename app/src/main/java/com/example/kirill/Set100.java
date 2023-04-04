package com.example.kirill;

import java.util.Arrays;

public class Set100 {
    boolean[] set = new boolean[101];
    int size = 0;
    public boolean add(int num) {
        if(num > 100 || num < 0) return false;
        if (set[num]) {
            return false;
        }else{
            size ++;
            return  set[num] = true;
        }
    }
    public boolean remove(int num) {
        if(num >= 0 && num <= 100 && set[num]) {
            set[num] = false;
            return true;
        }
        return false;
    }
    public boolean contains(int num) {
        return set[num];
    }

    public static void main(String[] args) {
        Set100 myset = new Set100();
        myset.add(10);
        myset.add(-5);
        myset.add(101);
        System.out.println(Arrays.toString(myset.set));
        System.out.println(myset.size);
        myset.remove(10);
        myset.remove(-5);
        myset.remove(100);

        System.out.println(myset.contains(100));
    }
}
