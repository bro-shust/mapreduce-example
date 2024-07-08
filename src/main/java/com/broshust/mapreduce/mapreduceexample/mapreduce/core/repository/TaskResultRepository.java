package com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository;

import java.util.List;

public interface TaskResultRepository<R> {

    void save(String jobId, R taskResult);

    List<R> findAll(String jobId);

    void deleteAll(String jobId);
}