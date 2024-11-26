package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.ProjectRepository;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;

	

	@Override
	public Student checkstudentlogin(String suname, String spwd) 
	{
		
		return studentRepository.checkstudentlogin(suname, spwd);
	}

	@Override
	public Student viewstudent(String suname) 
	{
		return studentRepository.viewstudent(suname);
	}

	@Override
	public int changestudentpassword(String soldpwd, String snewpwd, String suname) 
	{
		
		return studentRepository.updatestudentpassword(snewpwd, soldpwd, suname);
	}
	
	@Override
	public int getStudentIdByUsername(String uname) {
		return studentRepository.getIDbyUname(uname);
	}
	
	public boolean checkallocation(int conID)
	{
		
	 Student student = studentRepository.getconallocation(conID);
	 if(student==null) {
		 return true;
	 }
	 return student.isAllocated();
	}
	public void changeallocation(int conID) {
		 studentRepository.updateallocation(conID);
	}
	
	public List<Student> getAllStudent(){
		return (List<Student>) studentRepository.findAll();
	}
	@Override
	public Student getStudentByStudentID(int id) {
		return studentRepository.findStudentById(id);
	}
	@Override
	public boolean getStudentLeadStatus(int sid) {
		Student student = studentRepository.findStudentById(sid);
		if(student.isTeamLead()) return true;
		else return false;
	}
}
