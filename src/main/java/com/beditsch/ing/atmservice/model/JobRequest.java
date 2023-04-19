package com.beditsch.ing.atmservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class JobRequest implements Comparable<JobRequest> {
    private final int region;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final JobRequestType requestType;
    private final int atmId;

    public JobRequest(int region, JobRequestType requestType, int atmId) {
        this.region = region;
        this.requestType = requestType;
        this.atmId = atmId;
    }

    public int getRegion() {
        return region;
    }

    public JobRequestType getRequestType() {
        return requestType;
    }

    public int getAtmId() {
        return atmId;
    }

    @Override
    public int compareTo(@NotNull JobRequest o) {
        if (this.getRegion() != o.getRegion()) {
            return Integer.compare(this.getRegion(), o.getRegion());
        } else if (this.getAtmId() == o.getAtmId()) {
            return 0;
        } else {
            int priorityCompResult =  Integer.compare(
                    this.getRequestType().getPriority(), o.getRequestType().getPriority()
            );
            return priorityCompResult != 0? priorityCompResult : -1;
        }
    }
}
