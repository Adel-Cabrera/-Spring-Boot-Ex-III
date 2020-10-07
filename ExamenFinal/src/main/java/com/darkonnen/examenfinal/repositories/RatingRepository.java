package com.darkonnen.examenfinal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.examenfinal.entities.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>{	

//	@Query("SELECT u.username, r.rating FROM Rating r JOIN r.user u WHERE r.show.id = ?1")
//	List<Object[]> findShowRatings(Long id);
	
	List<Rating> findAll();
	
	Rating findRatingById(Long id);
	


}
