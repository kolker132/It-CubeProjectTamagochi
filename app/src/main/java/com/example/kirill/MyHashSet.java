package com.example.kirill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MyHashSet<T> {
    private ArrayList<T>[] set;

    public MyHashSet(int capacity) {
        set = new ArrayList[capacity];
        for (int i = 0; i < set.length; i++) {
            set[i] = new ArrayList<T>();
        }
    }

    public boolean add(T val) {
        int hash = val.hashCode();
        int col = hash % set.length;
        return set[col].add(val);
    }

    public boolean remove(T val) {
        int hash = val.hashCode();
        int col = hash % set.length;
        return set[col].remove(val);
    }

    public boolean contains(T val) {
        int hash = val.hashCode();
        int col = hash % set.length;
        ArrayList list = set[col];
        for (Object next : list) {
            if (next.equals(val)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // MyHashSet<Integer> mhs = new MyHashSet<>(10);
        //  Set<MyMonth> mhs = new HashSet<>(8);
        Set<MyMonth> mhs = new TreeSet<>();
        mhs.add(new MyMonth("Dec", -15, 31));
        mhs.add(new MyMonth("dec", -15, 31));
        mhs.add(new MyMonth("Jan", -15, 31));
        System.out.println(mhs);
    }
}
