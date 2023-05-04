package com.example.kirill;

import java.util.SortedSet;
import java.util.TreeSet;

public class Main3 {
    public static void main(String[] args) {
        //Создание пустого дерева
        TreeSet<Double> tree = new TreeSet<Double>();

        //Добавление элементов
        tree.add(0.1);
        tree.add(0.2);

        //Элементы выводятся в сортированном (лексикографическом) порядке
        //Классы должны быть Comparable
        System.out.println("===== Tree =====");
        for (double s : tree){
            System.out.println(s);
        }
        System.out.println();

        //Удаление элементов
        System.out.println(tree.remove(0.3));
        //удаление несуществующего элемента возвращает False
        System.out.println(tree.remove(0.1));

        System.out.println("===== Tree =====");
        for (double s : tree) {
            System.out.println(s);
        }
        System.out.println();

        tree.add(0.2);
        tree.add(0.8);
        tree.add(1.2);

        //Быстрая проверка наличия элемента
        System.out.println(tree.contains(0.8) + " " + tree.contains(0.0));

        // Выводит наименьший элемент, больший или равный указанного
        System.out.println("ceiling = " + tree.ceiling(0.8));
        System.out.println(tree.ceiling(0.56)); //null если нет такого элемента

        //Выводит наибольший, элемент меньший или равный указанного
        System.out.println("floor = " + tree.floor(21.3));
        System.out.println(tree.floor(5.4)); //null если нет такого элемента

        System.out.println("===== Subtree =====");
        //Взятие подмножества элементов >= zaa и < zzzz
        SortedSet<Double> subtree = tree.subSet(0.4, 7.2);
        for (Double s : subtree){
            System.out.println(s);
        }
        System.out.println();
    }
}
