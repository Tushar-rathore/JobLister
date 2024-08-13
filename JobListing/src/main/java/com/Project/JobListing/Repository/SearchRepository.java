package com.Project.JobListing.Repository;

import com.Project.JobListing.Model.Post;

import java.util.List;

public interface SearchRepository {
        List<Post> findByText(String text);


}
