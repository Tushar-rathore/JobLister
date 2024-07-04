package com.Project.JobListing.Repository;

import com.Project.JobListing.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
