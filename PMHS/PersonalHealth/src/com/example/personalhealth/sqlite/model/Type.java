package com.example.personalhealth.sqlite.model;

public class Type {
	 
    int id;
    String type_name;
 
    // constructors
    public Type() {
 
    }
 
    public Type(String type_name) {
        this.type_name = type_name;
    }
 
    public Type(int id, String type_name) {
        this.id = id;
        this.type_name = type_name;
    }
 
    // setter
    public void setId(int id) {
        this.id = id;
    }
 
    public void setTypeName(String type_name) {
        this.type_name = type_name;
    }
 
    // getter
    public int getId() {
        return this.id;
    }
 
    public String getTypeName() {
        return this.type_name;
    }
}
