package com.springlearning.linkedinScrapping.linkedin.dto.linkedin;

import lombok.Data;

import java.util.List;

@Data
public class LinkedInProfile {
    private String id;
    private String name;
    private String city;
    private String country_code;
    private String position;
    private String about;
    private String url;
    private String avatar;
    private CurrentCompany current_company;
    private List<Experience> experience;
    private String educations_details;
    private List<Education> education;
    private int followers;
    private int connections;
    private String current_company_company_id;
    private String current_company_name;
    private List<Project> projects;
    private String location;
    private String input_url;
    private String linkedin_id;
    private String linkedin_num_id;
    private String banner_image;
    private String timestamp;
    private List<Activity> activity;
    private List<SimilarProfile> similarProfiles;
    private Input input;
}
