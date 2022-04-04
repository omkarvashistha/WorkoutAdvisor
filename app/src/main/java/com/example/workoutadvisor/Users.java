package com.example.workoutadvisor;

public class Users {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    int Id;
    String UserName,password,role;

    public Users(int Id, String userName) {
        this.UserName = userName;
        this.Id = Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
