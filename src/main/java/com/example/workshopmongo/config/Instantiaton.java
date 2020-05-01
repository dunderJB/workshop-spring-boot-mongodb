package com.example.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AutorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiaton implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex,bob));		
		
		Post post1 = new Post(null, sdf.parse("19/04/2020"), "Viajando para São Paulo", "E ai galera to indo nessa me desejem sorte", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("25/04/2020"), "Cheguei em São Paulo Galeris", "Cheguei em são Paulo gora as 22:22 e estou super cansada", new AutorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!!", sdf.parse("25/04/2020"), new AutorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!!", sdf.parse("25/04/2020"), new AutorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia !!", sdf.parse("25/04/2020"), new AutorDTO(alex));
		
		post1.setComments(Arrays.asList(c1,c2));
		post2.setComments(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
