package com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import java.util.List;

public interface JobExecutor<J extends Job, R> {

   void execute(J job, List<R> taskResults);
}