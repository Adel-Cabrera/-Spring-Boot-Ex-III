package com.darkonnen.posts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darkonnen.posts.models.Post;
import com.darkonnen.posts.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAllPost(){
		return postRepository.findAll();
	}
	
	public Post findPostById(Long id) {
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isPresent()) {
			return optionalPost.get();
		} else {
			return null;
		}
	}

	public void createPost(Post post) {
		postRepository.save(post);
	} 
	
	public void updatePost(Post post) {
		postRepository.save(post);
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

	
	
	
}
