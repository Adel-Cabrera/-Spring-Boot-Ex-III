package com.darkonnen.examenfinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkonnen.examenfinal.entities.Rating;
import com.darkonnen.examenfinal.entities.Show;
import com.darkonnen.examenfinal.repositories.ShowRepository;

@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;
	
	// TODO implementar native query de showrepo

	// FIND ONE

	public Show findShowById(Long id) {
		Optional<Show> optionalShow = showRepository.findById(id);
		if (optionalShow.isPresent()) {
			return optionalShow.get();
		} else {
			return null;
		}

	}

	// FIND ALL

	public List<Show> findAllShows() {
		return showRepository.findAll();
	}

	// CREATE - SAVE

	public void saveShow(Show show) {
		showRepository.save(show);
	}

	// CREATE - UPDATE

	public void updateShow(Show show) {
		showRepository.save(show);
	}

	// DELETE - DESTROY

	public void deleteShow(Long id) {
		showRepository.deleteById(id);
	}
	
	
	public void updateRating(Long id, List<Rating> ratings) {
		Show show = showRepository.findShowById(id);
//		show.setAverage_rating();
		System.out.println(ratings);
		show.setRatings(ratings);
	}
	
	


}
