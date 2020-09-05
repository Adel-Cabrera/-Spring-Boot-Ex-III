package com.darkonnen.waterbnb.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darkonnen.waterbnb.models.Pool;
import com.darkonnen.waterbnb.repositories.PoolRepository;

@Transactional
@Service
public class PoolService {
	private PoolRepository poolRepository;
	
	public PoolService(PoolRepository poolRepository) {
		this.poolRepository = poolRepository;
	}
	
	public List<Object[]> findUserPoolListingsById(Long id) {
		return poolRepository.findUserPoolListingsById(id);
	}
	
	public void savePool(Pool pool) {
		poolRepository.save(pool);
	}
	
	public Pool findPoolById(Long id) {
		return poolRepository.findPoolById(id);
	}
	
	public void updatePool(Pool pool) {
		Date date = new Date();
		poolRepository.updatePoolDetails(pool.getDescription(), pool.getCost(), pool.getSize(), date, pool.getId());
	}
	
	public List<Pool> findPoolsByLocation(String searchQuery) {
		return poolRepository.findByAddressContaining(searchQuery);
	}
	
	public List<Pool> findAllPools() {
		return poolRepository.findAllPools();
	}
	
	public void updateRating(Long id) {
		Pool pool = poolRepository.findPoolById(id);
		pool.setAverage_reviews();
	}
}