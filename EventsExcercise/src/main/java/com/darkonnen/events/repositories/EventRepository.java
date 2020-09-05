package com.darkonnen.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.darkonnen.events.models.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	
	List<Event> findAll();
	
	@Query("SELECT e FROM Event e WHERE state = ?1")
	List<Event> findEventsInState(String state);
	
	@Query("SELECT e FROM Event e WHERE state <> ?1")
	List<Event> findEventsExcludingState(String state);
	
}