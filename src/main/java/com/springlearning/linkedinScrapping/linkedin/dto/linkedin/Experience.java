package com.springlearning.linkedinScrapping.linkedin.dto.linkedin;

import lombok.Data;

@Data
public class Experience {
    private String title;
    private String location;
    private String description_html;
    private String start_date;
    private String end_date;
    private String company;
    private String company_id;
    private String url;
    private String company_logo_url;

}
