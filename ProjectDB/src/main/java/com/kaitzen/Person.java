package com.kaitzen;

public class Person {
    private Integer id;
    private String name;
    Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (!name.isEmpty() && name != null) {
            this.name = name;
        }
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        if (id != null) {
            this.id = id;
        }
    }

    public String toString() {
        return "Nombre: " + name + ", ID: " + id;
    }
}

