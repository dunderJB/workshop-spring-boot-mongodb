package com.example.workshopmongo.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.Services.exception.ObjectNotFoundException;
import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRespository;
	
	public Optional<Post> findById(String id) {
		
		Optional<Post> post = postRespository.findById(id);
		if(post == null) {
			throw new ObjectNotFoundException("Post não encontrado");
		}
		
		return post;
	}
	
	public List<Post> findByTitle(String text) {
		return postRespository.findTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date dataMin, Date dataMax){
		dataMax = new Date(dataMax.getTime() + 24 * 60 * 60 * 1000);
		return postRespository.fullSearch(text, dataMin, dataMax);
	}
}
