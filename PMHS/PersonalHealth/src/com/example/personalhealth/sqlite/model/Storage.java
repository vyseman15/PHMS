package com.example.personalhealth.sqlite.model;

public class Storage {
	 
    int id;
    String name;
    String url;
    int status;
    String created_at;
 
    // constructors
    public Storage() {
    }
 
    public Storage(String name, int status, String url) {
        this.name = name;
        this.status = status;
        this.url = url;
    }
 
    public Storage(int id, String name, int status, String url) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.url = url;
    }
 
    // setters
    public void setId(int id) {
        this.id = id;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setStatus(int status) {
        this.status = status;
    }
    
    public void setUrl(String url){
    	this.url = url;
    }
     
    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }
 
    // getters
    public long getId() {
        return this.id;
    }
 
    public String getName() {
        return this.name;
    }
    
    public String getUrl(){
    	return this.url;
    }
 
    public int getStatus() {
        return this.status;
    }
}