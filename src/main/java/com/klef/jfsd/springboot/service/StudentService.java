package com.klef.jfsd.springboot.service;

import java.util.List;

import com.klef.jfsd.springboot.model.Student;

public interface StudentService 
{
	
	public int getStudentIdByUsername(String uname);
	public Student getStudentByStudentID(int id);
	public Student checkstudentlogin(String suname,String spwd);
	public Student viewstudent(String suname);
	public int changestudentpassword(String soldpwd,String snewpwd,String suname);
	public boolean checkallocation(int conID);
	public void changeallocation(int conID);
	public List<Student> getAllStudent();
	public boolean getStudentLeadStatus(int sid);

}
