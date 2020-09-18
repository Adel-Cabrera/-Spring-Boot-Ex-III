package com.darkonnen.posts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darkonnen.posts.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAll();

}
