package com.darkonnen.posts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.posts.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAll();
	
}


//@Repository
//public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
//	
//	List<Post> findAll();
//	
//}
