package com.beditsch.ing.atmservice.model;

public enum JobRequestType {
    FAILURE_RESTART(1),
    PRIORITY(2),
    SIGNAL_LOW(3),
    STANDARD(4);

    //the smaller the priority the more important the job request
    private final int priority;

    JobRequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
