package com.darkonnen.examenfinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.examenfinal.entities.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long>{
	
//	@Query(value="SELECT AVG(rating) FROM shows", nativeQuery=true)
//	double getAverageRating();
	
	List<Show> findAll();
	
	@Query("SELECT s FROM Show s WHERE s.id = ?1")
	Show findShowById(Long id);


}
