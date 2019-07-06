package com.rncs.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

	
    private int id;
    
    @NotEmpty(message = "Username can not be empty")
    @Size(min = 2, max = 20, message = "Username length must be between 2 and 20")
    private String userName;
    
  
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 20, message = "Name length must be between 10 and 40")
    private String name;
    
    
    @Email
    private String email;
    
    
    @NotEmpty(message = "Password can not be empty")
    private String password;
       
    public User() {
		super();
	}


	public User(int id, String userName, String name, String email, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
	}
    
    
    public User( String userName, String name, String email, String password) {
		super();
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
	}
    
    
    


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", name=" + name + ", email=" + email + ", password="
				+ password + "]";
	}
    
    
    
    
    

    
    
}