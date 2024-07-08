package com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;

public interface TaskExecutor<T extends Task, R> {

    R execute(T task);

    void handleError(T task, Exception e);
}