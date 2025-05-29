package com.springlearning.linkedinScrapping.linkedin.dto.linkedin;

import lombok.Data;

@Data
public class Education {
    private String title;
    private String degree;
    private String field;
    private String url;
    private String start_year;
    private String end_year;
    private String description;
    private String description_html;
    private String institute_logo_url;
}
