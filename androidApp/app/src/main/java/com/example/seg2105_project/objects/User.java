package com.example.seg2105_project.objects;

public abstract class User {
    private String name, email;
    public String role;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

//    public User(String username, String password) {
//        this.name = "";
//        this.age = -1;
//        this.username = username;
//        this.password = password;
//    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
