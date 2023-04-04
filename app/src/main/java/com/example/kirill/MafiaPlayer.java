package com.example.kirill;

import java.util.HashMap;
import java.util.Map;

public class MafiaPlayer {
    String name;
    String role;
    int point;

    public MafiaPlayer(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "MafiaPlayer{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static void main(String[] args) {
            Map<String , MafiaPlayer> hm = new HashMap<String, MafiaPlayer>();
            hm.put("Мафия", new MafiaPlayer("Вася", "Мафия"));
            hm.put("Житель", new MafiaPlayer("Петя", "Житель"));
            hm.put("Житель", new MafiaPlayer("Виктор", "Житель"));



        for (String key : hm.keySet()) {
            System.out.println("Key: " + key);
        }
        for (MafiaPlayer value : hm.values()) {
            System.out.println("Value: " + value);
        }
        for (Map.Entry entry : hm.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        Object o = hm.get("Житель");
        System.out.println(((MafiaPlayer)o).point);
    }
}
