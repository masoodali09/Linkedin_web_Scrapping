package com.springlearning.linkedinScrapping.linkedin.enums;


public enum SnapshotStatus {
    READY,
    FAILED,
    UNKNOWN,
    RUNNING;

    public static SnapshotStatus from(String status) {
        if (status == null || status.isBlank())
            return UNKNOWN;

        return SnapshotStatus.valueOf(status.toUpperCase());
    }
}
