package com.darkonnen.waterbnb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.waterbnb.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	@Query("SELECT g.first_name, g.last_name, r.review, r.rating FROM Review r JOIN r.guest g WHERE r.pool.id = ?1")
	List<Object[]> findPoolReviews(Long id);
}