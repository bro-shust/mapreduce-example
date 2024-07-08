package com.broshust.mapreduce.mapreduceexample.workflow.domain.model;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlLoadJob implements Job {

    private String id;
    private String workflowId;
    private Status status;
    private List<XmlLoadTask> tasks;
    private ZonedDateTime startedAt;
    private ZonedDateTime finishedAt;
    private String error;

    @Override
    public void complete() {
        status = Status.COMPLETED;
        finishedAt = ZonedDateTime.now();
    }

    @Override
    public void fail(Throwable throwable) {
        error = throwable.getMessage();
        status = Status.COMPLETED;
        finishedAt = ZonedDateTime.now();
    }

    public enum Status {
        IN_PROGRESS,
        COMPLETED
    }
}