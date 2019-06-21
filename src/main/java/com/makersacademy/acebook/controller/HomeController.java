package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Greeting;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostForm;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

	private final PostRepository postRepository;

	@Autowired
	public HomeController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/greeting")
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "postForm";
	}

	@GetMapping("/postzz")
	public String post(Model model) {
		model.addAttribute("post", new PostForm("change me"));
		return "postForm";
	}

	@PostMapping
	public String post(@ModelAttribute PostForm post, Model model) {
		Post newPost = new Post(post.getContent());
		postRepository.save(newPost);
		model.addAttribute("database", postRepository.findAll());
		return "result";
	}

	//Create a new GET endpoint which lists all the posts


}
