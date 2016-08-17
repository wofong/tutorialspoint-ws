package com.wf.spring.hello.rest;

import org.springframework.data.annotation.Id;

public class Person {
	@Id private String id;
	
	private String firstName;
	private String lastName;
	private double age;
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public double getAge(){
		return this.age;
	}
	public void setAge(double age){
		this.age=age;
	}
}
