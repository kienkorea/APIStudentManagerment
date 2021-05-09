package com.example.apistudentmanagerment.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String address;

    @Id
    @Column
    @GeneratedValue
    private long id;

    public StudentEntity(){

    }
    public StudentEntity(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
