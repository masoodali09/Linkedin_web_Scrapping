package com.springlearning.linkedinScrapping.linkedin.controller;

import com.springlearning.linkedinScrapping.linkedin.dto.LinkedInScrapeResponse;
import com.springlearning.linkedinScrapping.linkedin.dto.linkedin.LinkedInProfile;
import com.springlearning.linkedinScrapping.linkedin.enums.SnapshotStatus;
import com.springlearning.linkedinScrapping.linkedin.service.LinkedinScraperService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.*;

@Data
@RestController
@RequestMapping("/scrape")
public class LinkedinScrapeController {

    private final LinkedinScraperService linkedinScraperService;

    @PostMapping("/linkedinProfile")
    public ResponseEntity<?> scrapeLinkedinProfile(@RequestBody List<String> profileUrls) {
        String snapshotId = linkedinScraperService.getSnapshotId(profileUrls);
        if (snapshotId == null || snapshotId.isBlank()) {
            return ResponseEntity.status(500).body("Failed to trigger scrape.");
        }
        return ResponseEntity.ok(Collections.singletonMap("snapshotId", snapshotId));    }


    @GetMapping("/linkedinProfile/{snapshotId}")
    public ResponseEntity<?> getScrapeResult(@PathVariable String snapshotId) {
        SnapshotStatus snapshotStatus = SnapshotStatus.from(linkedinScraperService.getSnapshotStatus(snapshotId));

        return switch (snapshotStatus) {
            case READY -> {
                List<LinkedInProfile> results = linkedinScraperService.fetchSnapshotData(snapshotId);
                yield ResponseEntity.ok(new LinkedInScrapeResponse("Snapshot ready", results));
            }
            case FAILED -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LinkedInScrapeResponse("Snapshot failed", null));
            case UNKNOWN -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LinkedInScrapeResponse("Snapshot not found or invalid", null));
            case RUNNING -> ResponseEntity.status(HttpStatus.ACCEPTED).body(new LinkedInScrapeResponse("Snapshot is still processing", null));
        };
    }
}