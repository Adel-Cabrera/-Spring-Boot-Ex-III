package com.darkonnen.pagination.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.pagination.models.Dojo;

@Repository
public interface DojoRepository extends PagingAndSortingRepository<Dojo, Long> {
	@Query("SELECT d, n FROM Dojo d JOIN d.ninjas n")
	Page<Object[]> allDojosAndNinjas(PageRequest pageRequest);
}