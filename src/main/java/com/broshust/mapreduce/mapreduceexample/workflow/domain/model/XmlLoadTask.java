package com.broshust.mapreduce.mapreduceexample.workflow.domain.model;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XmlLoadTask implements Task {

    private String id;
    private String jobId;
    private String workflowId;
    private String filePath;
    private Status status;
    private String error;

    @Override
    public void complete() {
        status = Status.COMPLETED;
    }

    @Override
    public void fail(Throwable e) {
        error = e.getMessage();
        status = Status.FAILED;
    }

    public boolean isDone() {
        return status != Status.INITIALIZED;
    }

    public enum Status {
        INITIALIZED,
        COMPLETED,
        FAILED
    }
}