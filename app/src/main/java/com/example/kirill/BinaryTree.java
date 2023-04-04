package com.example.kirill;

import java.sql.SQLOutput;

public class BinaryTree<T extends Comparable> {
    private T value;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(T nodeValue) {
        this.value = nodeValue;
    }

    public boolean add(T val) {
        if (val.compareTo(this.value) == 0) {
            return false;
        } else if (val.compareTo(this.value) < 0) {
            if (this.left == null) {
                this.left = new BinaryTree(val);
                return true;
            }else {
                return  this.left.add(val);
            }
        }else {
            if (this.right == null) {
                this.right = new BinaryTree(val);
                return true;
            }else {
                return  this.right.add(val);
            }
        }
    }
    private void print() {
        print("");
    }
    public void print(String s ) {
        if (this.right != null) {
            this.right.print(s + "\t");
        }

        System.out.println(s + this.value);
        if (this.left != null) {
            this.left.print(s + "\t");
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>("K");
        tree.add("H");
        tree.add("A");
        tree.add("B");
        tree.add("Q");
        tree.add("Z");
        tree.add("Y");
        System.out.println(tree);
        tree.print();
    }



    @Override
    public String toString() {
        return "BinaryTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
