package com.darkonnen.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event findByName(String name);

}