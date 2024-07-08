package com.broshust.mapreduce.mapreduceexample.mapreduce.core.model;

import java.util.List;

public interface Job {

    String getId();

    List<? extends Task> getTasks();

    void complete();

    void fail(Throwable throwable);
}