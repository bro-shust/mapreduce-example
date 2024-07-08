package com.broshust.mapreduce.mapreduceexample.mapreduce.core.model;

public interface Task {

    String getId();

    String getJobId();

    void complete();

    void fail(Throwable throwable);
}