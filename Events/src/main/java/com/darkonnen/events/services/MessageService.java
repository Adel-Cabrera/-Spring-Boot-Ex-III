package com.darkonnen.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.darkonnen.events.models.Message;
import com.darkonnen.events.repositories.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void create(Message message) {
    	messageRepository.save(message);
    }
    
//    public Message findById(Long id) {
//        return messageRepository.findById(id);
//    }
    
	public Message findById(Long id) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			return optionalMessage.get();
		} else {
			return null;
		}
	}

    public List<Message> allMessages() {
    	return (List<Message>) messageRepository.findAll();
    }
    public void delete(Long id) {
    	messageRepository.deleteById(id);
    }
    public void update(Message message) {
    	messageRepository.save(message);
    }

}