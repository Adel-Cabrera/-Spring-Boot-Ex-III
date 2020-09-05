package com.darkonnen.waterbnb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.waterbnb.models.Pool;

@Repository
public interface PoolRepository extends CrudRepository<Pool, Long> {
	
	@Query("SELECT p.id, p.address, p.size, p.cost, p.average_reviews FROM Pool p WHERE p.host.id = ?1")
	List<Object[]> findUserPoolListingsById(Long id);
	
	@Query("SELECT p FROM Pool p WHERE p.id = ?1")
	Pool findPoolById(Long id);
	
	@Modifying
	@Query("update Pool p set p.description = ?1, p.cost = ?2, p.size = ?3, p.updatedAt = ?4 WHERE p.id = ?5")
	int updatePoolDetails(String description, Float cost, String size, Date date, Long id);
	
	List<Pool> findByAddressContaining(String search);

	@Query("SELECT p FROM Pool p")
	List<Pool> findAllPools();
}