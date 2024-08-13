//package com.Project.JobListing.Controller;
//
//
//import com.Project.JobListing.Model.Post;
//import com.Project.JobListing.Repository.PostRepository;
//import com.Project.JobListing.Repository.SearchRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//
//public class PostController {/*
//    @Autowired
//    PostRepository repo;
//
//
//    @ApiIgnore
//    @RequestMapping(value="/")
//    public void redirect(HttpServletResponse response) throws IOException{
//    response.sendRedirect ("/swagger-ui.html");
//}
//
//@GetMapping("/posts")
//public List<Post> getAllPost(){
//return repo.findAll();
//}
//
//@GetMapping("/posts/{text}")
//public List<Post> Search(@PathVariable String text){
//        return repo.findAll();
//}
//
//@PostMapping("/post")
//public Post addPost(@RequestBody Post post){
//return repo.save(post);
//}
//
//}*/
//
//        @Autowired
//        PostRepository postRepository;
//
//        @Autowired
//        SearchRepository srepo;
//
//        @ApiIgnore
//        @RequestMapping(value = "/")
//        public void redirect(HttpServletResponse response) throws IOException {
//            response.sendRedirect("/swagger-ui.html");
//        }
//
//        @GetMapping("/allPosts")
//        @CrossOrigin
//        public List<Post> getAllPosts() {
//            return postRepository.findAll();
//        }
//
//        // posts/java
//        @GetMapping("/posts/{text}")
//        @CrossOrigin
//        public List<Post> search(@PathVariable String text) {
//            return srepo.findByText(text);
//        }
//
//        @PostMapping("/post")
//        @CrossOrigin
//        public Post addPost(@RequestBody Post post) {
//            return postRepository.save(post);
//        }
//
//    }
//


//
//package com.Project.JobListing.Controller;
//
//import com.Project.JobListing.Model.Post;
//import com.Project.JobListing.Repository.PostRepository;
//import com.Project.JobListing.Repository.SearchRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class PostController {
//
//        @Autowired
//        PostRepository postRepository;
//
//        @Autowired
//        SearchRepository srepo;
//
//        @ApiIgnore
//        @RequestMapping(value = "/")
//        public void redirect(HttpServletResponse response) throws IOException {
//                response.sendRedirect("/swagger-ui.html");
//        }
//
//        @GetMapping("/allPosts")
//        @CrossOrigin
//        public List<Post> getAllPosts() {
//                return postRepository.findAll();
//        }
//
//        @GetMapping("/posts/{text}")
//        @CrossOrigin
//        public List<Post> search(@PathVariable String text) {
//                return srepo.findByText(text);
//        }
//
//        @PostMapping("/post")
//        @CrossOrigin
//        public Post addPost(@RequestBody Post post) {
//                return postRepository.save(post);
//        }
//
//        @PutMapping("/posts/{id}")
//        @CrossOrigin
//        public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
//                Optional<Post> postOptional = postRepository.findById(id);
//
//                if (postOptional.isPresent()) {
//                        Post post = postOptional.get();
//                        post.setProfile(updatedPost.getProfile());
//                        post.setDescription(updatedPost.getDescription());
//                        post.setExperience(updatedPost.getExperience());
//                        post.setTechnologies(updatedPost.getTechnologies());
//
//                        return postRepository.save(post);
//                } else {
//                        // Handle the case when the post with the given ID does not exist
//                        throw new RuntimeException("Post not found with id " + id);
//                }
//        }
//
//
//        @DeleteMapping("/post/{id}")
//        @CrossOrigin
//        public String deletePost(@PathVariable String id) {
//                try {
//                        postRepository.deleteById(id);
//                        return "Post with id " + id + " deleted successfully.";
//                } catch (Exception e) {
//                        return "Error deleting post with id: " + id + ". " + e.getMessage();
//                }
//        }
//}



//using streams
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PostController {

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
                return postRepository.findAll().stream()
                        //     .filter(post -> post.getExperience() > 0) // Example: Only return posts with experience > 0
                        .collect(Collectors.toList());
        }


        @GetMapping("/posts/{text}")
        @CrossOrigin
        public List<Post> search(@PathVariable String text) {
                return srepo.findByText(text);
      }

        @PostMapping("/post")
        @CrossOrigin
        public Post addPost(@RequestBody Post post) {
                post.setProfile(Optional.ofNullable(post.getProfile()).orElse("Unknown Profile")); // Default profile if not provided
                return postRepository.save(post);
        }


        @PutMapping("/posts/{id}")
        @CrossOrigin
        public Post updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
                return postRepository.findById(id)
                        .map(post -> {
                                Optional.ofNullable(updatedPost.getProfile()).ifPresent(post::setProfile);
                                Optional.ofNullable(updatedPost.getDescription()).ifPresent(post::setDescription);
                                Optional.ofNullable(updatedPost.getExperience()).ifPresent(post::setExperience);
                                Optional.ofNullable(updatedPost.getTechnologies()).ifPresent(post::setTechnologies);
                                return postRepository.save(post);
                        })
                        .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        }

        @DeleteMapping("/post/{id}")
        @CrossOrigin
        public String deletePost(@PathVariable String id) {
                try {
                        postRepository.deleteById(id);
                        return "Post with id " + id + " deleted successfully.";
                } catch (Exception e) {
                        return "Error deleting post with id: " + id + ". " + e.getMessage();
                }
        }
}