package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService 
{
	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public Faculty checkfacultylogin(String funame, String fpwd) 
	{
		Faculty faculty = facultyRepository.checkfacultylogin(funame, fpwd);
		
		return faculty;
				
	}

	@Override
	public Faculty viewfaculty(String funame) 
	{
		
		return facultyRepository.viewfaculty(funame);
	}

	@Override
	public int changefacultypassword(String foldpwd, String fnewpwd, String funame)
	{
		
		return facultyRepository.updatefacultypassword(fnewpwd, foldpwd, funame);
	}

	@Override
	public void updateproalloted(int fid) {
		facultyRepository.updateproalloted(fid);
		
	}

	@Override
	public int FacultyIdByUsername(String funame) 
	{
	  return facultyRepository.FacultyIdByUsername(funame);
		
		
	}
	@Override
	public Faculty getFacultyById(int fmentorid) {
		return facultyRepository.findFacultyByID(fmentorid);
	}
	
}
