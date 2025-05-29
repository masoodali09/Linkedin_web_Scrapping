package com.springlearning.linkedinScrapping.linkedin.service;


import com.springlearning.linkedinScrapping.linkedin.dto.LinkedInScrapeRequest;
import com.springlearning.linkedinScrapping.linkedin.dto.SnapshotResponse;
import com.springlearning.linkedinScrapping.linkedin.dto.linkedin.LinkedInProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Data
@Slf4j
@Service
public class LinkedinScraperService {

    private final WebClient brightDataWebClient;

    @Value("${brightdata.api.datasetId}")
    private String datasetId;

    public String getSnapshotId(List<String> profileUrls) {
        List<LinkedInScrapeRequest> requestBody = profileUrls.stream()
                .map(LinkedInScrapeRequest::new)
                .toList();
        SnapshotResponse response = brightDataWebClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("trigger")
                        .queryParam("dataset_id", datasetId)
                        .queryParam("format", "json")
                        .queryParam("uncompressed_webhook", "true")
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(SnapshotResponse.class)
                .block();
        return response != null ? response.getSnapshotId() : null;
    }

    public String getSnapshotStatus(String snapshotId) {
        String progressUri = UriComponentsBuilder.fromPath("progress/" + snapshotId).toUriString();

        SnapshotResponse progress = brightDataWebClient.get()
                .uri(progressUri)
                .retrieve()
                .bodyToMono(SnapshotResponse.class)
                .block();
        return progress != null ? progress.getStatus() : "unknown";
    }

    public List<LinkedInProfile> fetchSnapshotData(String snapshotId) {
        String uri = UriComponentsBuilder.fromPath("snapshot/" + snapshotId)
                .queryParam("format", "json")
                .toUriString();
        return brightDataWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(LinkedInProfile.class)
                .collectList()
                .blockOptional()
                .orElse(Collections.emptyList());
    }
}
