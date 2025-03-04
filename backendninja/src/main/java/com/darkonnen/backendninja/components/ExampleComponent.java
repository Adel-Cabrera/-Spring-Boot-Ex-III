package com.darkonnen.backendninja.components;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.darkonnen.backendninja.repositories.CourseJpaRepository;

@Component("exampleComponent")
public class ExampleComponent {
	
	private static final Log LOG = LogFactory.getLog(ExampleComponent.class);
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository courseJpaRepository;
	
	public void sayHello() {
//		courseJpaRepository.
		LOG.info("HELLO FROM EXAMPLE COMPONENT");
	}
	

}
