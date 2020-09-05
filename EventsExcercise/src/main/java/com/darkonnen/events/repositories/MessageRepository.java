package com.darkonnen.events.repositories;

import org.springframework.data.repository.CrudRepository;

import com.darkonnen.events.models.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}