package com.springlearning.linkedinScrapping.linkedin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SnapshotResponse {
    @JsonProperty("snapshot_id")
    private String snapshotId;
    @JsonProperty("dataset_id")
    private String datasetId;
    private String status;
}
