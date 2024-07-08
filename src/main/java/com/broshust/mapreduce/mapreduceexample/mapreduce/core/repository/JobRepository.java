package com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import java.util.List;

public interface JobRepository<J extends Job, T extends Task> {

    J saveJob(J job);

    void updateJobStatus(J job);

    List<J> findReadyToCompleteJobs();

    J getJob(String jobId);

   void updateTaskStatus(T task);
}