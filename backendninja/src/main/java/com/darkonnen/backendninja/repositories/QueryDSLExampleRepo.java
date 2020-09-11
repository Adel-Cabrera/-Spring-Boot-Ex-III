package com.darkonnen.backendninja.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.darkonnen.backendninja.entities.Course;
import com.darkonnen.backendninja.entities.QCourse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

@Repository("queryDSLExampleRepo")
public class QueryDSLExampleRepo {
	
	private QCourse qCourse = QCourse.course;
	
	@PersistenceContext
	private EntityManager em;
	
	public void find(boolean exist) {
		
		JPAQuery<Course> query = new JPAQuery<Course>(em);
		
//		Predicate predicate1 = qCourse.description.endsWith("e");
		BooleanBuilder predicateBuilder = new BooleanBuilder(qCourse.description.endsWith("e"));
		
		if(exist) {
			Predicate predicate2 = qCourse.id.eq(123);
			predicateBuilder.and(predicate2);
		} else {
			Predicate predicate3 = qCourse.name.endsWith("a");
			predicateBuilder.or(predicate3);
		}
		Course course1 = query.select(qCourse).from(qCourse).where(predicateBuilder).fetchOne();

//		List<Course> courses = query.select(qCourse).from(qCourse).where(qCourse.hours.between(20, 50)).fetch();
	}
	
	
	
	
}
