package com.Project.JobListing.Controller;


import com.Project.JobListing.Model.Post;
import com.Project.JobListing.Repository.PostRepository;
import com.Project.JobListing.Repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController

//@CrossOrigin(origins = "http://localhost:3000")
public class PostController {/*
    @Autowired
    PostRepository repo;


    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException{
    response.sendRedirect ("/swagger-ui.html");
}

@GetMapping("/posts")
public List<Post> getAllPost(){
return repo.findAll();
}

@GetMapping("/posts/{text}")
public List<Post> Search(@PathVariable String text){
        return repo.findAll();
}

@PostMapping("/post")
public Post addPost(@RequestBody Post post){
return repo.save(post);
}

}*/

        @Autowired
        PostRepository postRepository;

        @Autowired
        SearchRepository srepo;

        @ApiIgnore
        @RequestMapping(value = "/")
        public void redirect(HttpServletResponse response) throws IOException {
            response.sendRedirect("/swagger-ui.html");
        }

        @GetMapping("/allPosts")
        @CrossOrigin
        public List<Post> getAllPosts() {
            return postRepository.findAll();
        }

        // posts/java
        @GetMapping("/posts/{text}")
        @CrossOrigin
        public List<Post> search(@PathVariable String text) {
            return srepo.findByText(text);
        }

        @PostMapping("/post")
        @CrossOrigin
        public Post addPost(@RequestBody Post post) {
            return postRepository.save(post);
        }

    }