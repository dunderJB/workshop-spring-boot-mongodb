package com.example.workshopmongo.resources;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.Services.PostService;
import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.resources.util.URL;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Post>> findById(@PathVariable String id){
		Optional<Post> post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	} 
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text, @RequestParam(value = "dataMin", defaultValue = "") String dataMin, @RequestParam(value = "dataMax", defaultValue = "") String dataMax){
		text = URL.decodeParam(text);
		Date min = URL.convertDate(dataMin, new Date(0L));
		Date max = URL.convertDate(dataMax, new Date());
		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
