package com.klef.jfsd.springboot.model;

import javax.persistence.*;

@Entity
@Table(name = "person_table")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {
	@Id
	@GeneratedValue
	private int id;
	@Column(nullable=false,length=200)
	private String name;
	@Column(nullable=false,length=10)
	private String gender;
	@Column(nullable=false,length=200)
	private String department;
	@Column(nullable = false,unique = true,length = 200)
	private String mailid;
	@Column(nullable = false,unique = true,length = 200)
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(nullable = false,length = 200)
    private String password;
	@Column(nullable = false,length = 200)
	private String contactno;
	@Column(nullable = false,length = 200)
	private String location;
	
}
