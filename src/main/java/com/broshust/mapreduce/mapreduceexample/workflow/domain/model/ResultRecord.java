package com.broshust.mapreduce.mapreduceexample.workflow.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRecord {

    private String id;
    private String jobId;
    private String data;
}
