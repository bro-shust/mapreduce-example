package com.broshust.mapreduce.mapreduceexample.mapreduce.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class JobParams {

    String jobId;
    String workflowId;

    public static JobParams createFrom(Job job) {
        return JobParams.builder()
                        .jobId(job.getId())
                        .build();
    }
}
