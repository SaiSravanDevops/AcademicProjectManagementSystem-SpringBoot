package com.klef.jfsd.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Review;
import com.klef.jfsd.springboot.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewRepository repository;
	
	@Override
	public List<Review> viewreviewsByProjectId(int pid) {
		return repository.viewReviewsByProjectID(pid);
	}
	@Override
	public void addreview(Review r) {
		repository.save(r);
	}
	@Override
	public void editstatustosubmitted(int rid) {
		repository.editstatustosubmitted(rid);
		
	}
	@Override
	public void editstatustocompleted(int rid) {
		repository.editstatustocompleted(rid);
	}
	@Override
	public int getAllReviewsByProjectId(int pid) {
		return repository.getAllReviewsByProjectId(pid);
	}
	@Override
	public int getCompletedReviewsByProjectId(int pid) {
		return repository.getCompletedReviewsByProjectId(pid);
	}
}

