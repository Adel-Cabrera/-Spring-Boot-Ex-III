package com.darkonnen.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.events.models.Event;
import com.darkonnen.events.repositories.EventRepository;

@Service
public class EventService {
    private EventRepository eventRepository;
    
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void create(Event event) {
    	eventRepository.save(event);
    }
//    public Event findById(Long id) {
////        return eventRepository.findOne(id);
//        return eventRepository.findById(id);
//    }
    
	public Event findById(Long id) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if (optionalEvent.isPresent()) {
			return optionalEvent.get();
		} else {
			return null;
		}
	}

    public List<Event> allEvents() {
    	return (List<Event>) eventRepository.findAll();
    }
    public void update(Event event) {

    	eventRepository.save(event);
    }
    public void delete(Long id) {
    	eventRepository.deleteById(id);
    }
}
