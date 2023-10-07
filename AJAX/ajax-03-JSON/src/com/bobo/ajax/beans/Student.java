package com.bobo.ajax.beans;

/**
 * ClassName: Student
 * PackageName: com.bobo.ajax.beans
 * Description:
 *
 * @Author CuiBo
 * @Create 2023/10/4 15:42
 * @Version 1.0
 */
public class Student {
    private String name;
    private String gender;
    private String address;

    public Student() {
    }

    public Student(String name, String gender, String address) {
        this.name = name;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
