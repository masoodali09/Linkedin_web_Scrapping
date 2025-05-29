package com.springlearning.linkedinScrapping.linkedin.dto;

import com.springlearning.linkedinScrapping.linkedin.dto.linkedin.LinkedInProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LinkedInScrapeResponse {
    private String message;
    private List<LinkedInProfile> data;
}
