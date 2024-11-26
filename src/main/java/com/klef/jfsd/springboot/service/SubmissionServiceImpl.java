package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Submission;
import com.klef.jfsd.springboot.repository.SubmissionRepository;

@Service
public class SubmissionServiceImpl implements SubmissionService{
	@Autowired
	SubmissionRepository repository;
	
	@Override
	public Submission addsubmission(Submission s) {
		return repository.save(s);
	}
	@Override
	public List<Submission> viewsubmission() {
		return (List<Submission>) repository.findAll();
	}
	@Override
	public Submission viewsubmissionById(int sid) {
		return repository.viewSubmissionById(sid);
	}
	@Override
	public void editratingfeedback(int rating, String feedback, int sid) {
		repository.editratingfeedbackById(rating , feedback, sid);
		
	}
	@Override
	public Submission getSubmissionByrid(int rid) {
		return repository.getSubmissionBrid(rid);
	}

	
}
