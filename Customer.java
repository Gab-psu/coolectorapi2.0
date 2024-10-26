package com.tired.test5;


public class Customer {

    private int id;
    private String name;
    private String email;
    private int age;

    @Override
    public String toString() {
        return "Customer [id=" + getId() + ", name=" + getName() + ", email=" + getEmail() + ", age=" + getAge() + "]";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
