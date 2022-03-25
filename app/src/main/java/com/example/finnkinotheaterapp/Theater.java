package com.example.finnkinotheaterapp;

public class Theater {
    private final String id;
    private final String name;

    public Theater(String a, String b) {
        id = a;
        name = b;
    }
    public String getID() {return id;}
    public String getName() {return name;}
}
