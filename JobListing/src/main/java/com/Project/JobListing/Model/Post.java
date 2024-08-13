//package com.Project.JobListing.Model;
//
//
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.Arrays;
//import java.util.Date;
//
//
//@Document(collection = "JobPost")
//public class Post {
//    private String profile;
//    private String desc;
//    private int exp;
//    private String techs[];
//
//    public Post() {
//    }
//
//    public String getProfile() {
//        return profile;
//    }
//
//    public void setProfile(String profile) {
//        this.profile = profile;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public int getExp() {
//        return exp;
//    }
//
//    public void setExp(int exp) {
//        this.exp = exp;
//    }
//
//    public String[] getTechs() {
//        return techs;
//    }
//
//    public void setTechs(String[] techs) {
//        this.techs = techs;
//    }
//
//    @Override
//    public String toString() {
//        return "Post{" +
//                "profile='" + profile + '\'' +
//                ", desc='" + desc + '\'' +
//                ", exp=" + exp +
//                ", techs=" + Arrays.toString(techs) +
//                '}';
//    }
//        }

package com.Project.JobListing.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Date;

@Document(collection = "JobPost")
public class Post {

    @Id
    private String id; // Added ID field to uniquely identify posts

    private String profile;
    private String description; // Renamed 'desc' to 'description' for better clarity
    private int experience; // Renamed 'exp' to 'experience' for clarity
    private String[] technologies; // Renamed 'techs' to 'technologies' for better clarity
    private Date postedDate; // Added a postedDate field to track when the job was posted

    public Post() {
        this.postedDate = new Date(); // Automatically set the posted date when a new post is created
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String[] getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String[] technologies) {
        this.technologies = technologies;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                ", experience=" + experience +
                ", technologies=" + Arrays.toString(technologies) +
                ", postedDate=" + postedDate +
                '}';
    }
}
