package com.darkonnen.backendninja.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.darkonnen.backendninja.models.Person;
import com.darkonnen.backendninja.services.ExampleService;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
	
	private static final Log LOG = LogFactory.getLog(ExampleServiceImpl.class);

	@Override
	public List<Person> getListPeople() {
		List<Person> people = new ArrayList();
		people.add(new Person("name1", 1));
		people.add(new Person("name2", 2));
		people.add(new Person("name3", 3));
		people.add(new Person("name4", 4));
		people.add(new Person("name5", 5));
		LOG.info("HELLO FROM SERVICE");
		return people;
	}

}
