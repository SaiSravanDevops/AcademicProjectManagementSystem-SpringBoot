package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.repository.ProjectRepository;
@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public Project addproject(Project project) {

		return projectRepository.save(project);
	}
	public List<Project> viewuserprojects(int id) 
	{
		
		return (List<Project>) projectRepository.viewProjectByUser(id); 
	}
    @Override
	public Project viewUserProjectByID(int pid) {
		return projectRepository.viewUserProjetbyID(pid);
	}
    @Override
    public void allocatefaculty(int fmentorid, int pid) 
    {
    	projectRepository.allocateFaculty(fmentorid,pid);
	
	
    }
    @Override
    public int checkmentorallocation(int pid) {
    	
    	Project p = viewUserProjectByID(pid);
    	return p.getFmentorid(); 
 
    }
	@Override
	public List<Project> viewfacultyprojects(int id) {
		 
		return (List<Project>) projectRepository.viewProjectUnderFaculty(id);
	}
}
