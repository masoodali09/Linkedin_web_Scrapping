package com.springlearning.linkedinScrapping.linkedin.dto.linkedin;

import lombok.Data;

@Data
public class Activity {
    private String interaction;
    private String link;
    private String title;
    private String img;
    private String id;
}
