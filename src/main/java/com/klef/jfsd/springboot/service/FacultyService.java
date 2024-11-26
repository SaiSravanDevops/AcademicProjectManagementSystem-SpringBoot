package com.klef.jfsd.springboot.service;

import com.klef.jfsd.springboot.model.Faculty;

public interface FacultyService 
{
	
	public Faculty checkfacultylogin(String funame,String fpwd);
	public Faculty viewfaculty(String fname);
	public int changefacultypassword(String foldpwd,String fnewpwd,String funame);
	public void updateproalloted(int fid);
	public int FacultyIdByUsername(String funame);
	public Faculty getFacultyById(int fmentorid);


}
