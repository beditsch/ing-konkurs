package com.beditsch.ing.atmservice;

import com.beditsch.ing.atmservice.model.JobRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.SortedSet;

@RestController
@RequestMapping("atms")
public class AtmServiceController {
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "calculateOrder"
    )
    public Collection<JobRequest> calculateOrder(@NotNull @RequestBody SortedSet<JobRequest> jobRequests) {
        return jobRequests;
    }
}
