package com.darkonnen.examenfinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkonnen.examenfinal.entities.Rating;
import com.darkonnen.examenfinal.entities.Show;
import com.darkonnen.examenfinal.entities.User;
import com.darkonnen.examenfinal.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
//	public List<Object[]> findShowRating(Long id) {
//		return ratingRepository.findShowRatings(id);
//	}
	
	public List<Rating> findAllRatings(){
		return ratingRepository.findAll();
	}
	
	public Rating findRatingById(Long id) {
		return ratingRepository.findRatingById(id);
	}
	
	public void addRating(Rating rating, Show show, User user) {		
		ratingRepository.save(rating);		
	}
	



}
