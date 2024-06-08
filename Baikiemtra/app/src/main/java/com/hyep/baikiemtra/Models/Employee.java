package com.hyep.baikiemtra.Models;

public class Employee {
    private String id, name, email, position, image, phone, idDepartment;

    public Employee(String id, String name, String email, String position, String image, String phone, String idDepartment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.image = image;
        this.phone = phone;
        this.idDepartment = idDepartment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
}
