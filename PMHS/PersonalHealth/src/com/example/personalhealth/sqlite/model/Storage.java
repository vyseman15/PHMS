package com.example.personalhealth.sqlite.model;

public class Storage {
	 
    int id;
    String name;
    String url;
    int status;
    String created_at;
    String userName;
 
    // constructors
    public Storage() {
    }
 
    public Storage(String name, int status, String url, String userName) {
        this.name = name;
        this.status = status;
        this.url = url;
        this.userName = userName;
    }
 
    public Storage(int id, String name, int status, String url,String userName) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.url = url;
        this.userName = userName;
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
    
    public void setUserName(String userName){
    	this.userName = userName;
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
    public String getUserName(){
    	return this.userName;
    }
 
    public int getStatus() {
        return this.status;
    }
}