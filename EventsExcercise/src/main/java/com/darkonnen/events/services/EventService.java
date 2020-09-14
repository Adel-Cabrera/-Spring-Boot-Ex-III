package com.darkonnen.events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.events.models.Event;
import com.darkonnen.events.models.Message;
import com.darkonnen.events.models.User;
import com.darkonnen.events.repositories.EventRepository;
import com.darkonnen.events.repositories.MessageRepository;
import com.darkonnen.events.repositories.UserRepository;

public class EventService {
	private EventRepository eventRepository;
	private UserRepository userRepository;
	private MessageRepository messageRepository;
	
	public EventService(EventRepository eventRepository, UserRepository userRepository, MessageRepository messageRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}
	
	public List<Event> getAll(){
		return this.eventRepository.findAll();
	}
	
	
	public void deleteEvent(Long id) {
		this.eventRepository.deleteById(id);
	}
	
	public void saveEvent(User user, Event event) {
		event.setHost(user);
		List<User> joiners = new ArrayList<>();
		joiners.add(user);
		event.setJoiners(joiners);
		this.eventRepository.save(event);
	}
	
	public void editEvent(Long id, Event changedEvent) {
		Optional<Event> original = eventRepository.findById(id);
		if (original.isPresent()) {
			original.setName(changedEvent.getName());
			original.setDate(changedEvent.getDate());
			original.setCity(changedEvent.getCity());
			original.setState(changedEvent.getState());
			eventRepository.save(original);

		}  

	}
	
	public void joinEvent(User user, Long eventID) {
		List<Event> joinedEvents = user.getJoinedEvents();
		Event event = this.eventRepository.findById(eventID);
		if(!joinedEvents.contains(event)) {
			joinedEvents.add(event);
			user.setJoinedEvents(joinedEvents);
			this.userRepository.save(user);
		}
	}
	
	public void leaveEvent(User user, Long eventID) {
		List<Event> joinedEvents = user.getJoinedEvents();
		Event event = eventRepository.findById(eventID);
		int indexOfEvent = joinedEvents.indexOf(event);
		if(indexOfEvent > -1 && !event.getHost().equals(user)) { // if the event is in the arraylist and the user isn't the host
			joinedEvents.remove(indexOfEvent);
		}
		user.setJoinedEvents(joinedEvents);
		userRepository.save(user);	
	}
	
	public Event getEventById(Long eventID) {
		return eventRepository.findById(eventID);
	}
	
	public void addMessage(Message message, Long eventID, User user) {
		Event event = this.eventRepository.findById(eventID);
		message.setEvent(event);
		message.setAuthor(user);
		this.messageRepository.save(message);
	}	

}
