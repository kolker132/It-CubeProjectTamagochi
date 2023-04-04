package com.example.kirill;

import java.util.Arrays;
import java.util.Comparator;

public class ReverseComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

    public static void main(String[] args) {
        Integer[] a = {1,2,3,4,5,6};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        Arrays.sort(a, new ReverseComparator());
        System.out.println(Arrays.toString(a));
    }
}
