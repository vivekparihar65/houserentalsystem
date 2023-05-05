package com.example.spunn.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private  String gender;
    private String dateofbirth;
    private String address;
        public User() {}

            public User(String name)
            {
                this.name=name;
            }
            public  User(String email,String password,String role)
            {
                this.name="";
                this.password="";
                this.email=email;
                this.phone="";
                this.role=role;
                this.gender="";
                this.dateofbirth="";
                this.address="";
            }

            public Map<String,Object>toMap()
            {
                HashMap<String,Object>result= new HashMap<>();
                result.put("name",name);
                return result;
            }

            public String getUsername() {
                return username;

            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDateofbirth() {
                return dateofbirth;
            }

            public void setDateofbirth(String dateofbirth) {
                this.dateofbirth = dateofbirth;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
}
