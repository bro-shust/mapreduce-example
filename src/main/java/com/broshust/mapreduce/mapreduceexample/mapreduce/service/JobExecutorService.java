package com.broshust.mapreduce.mapreduceexample.mapreduce.service;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.JobExecutor;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.JobParams;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.TaskResultRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
public class JobExecutorService<J extends Job, T extends Task, R> {

    private final JobRepository<J, T> jobRepository;
    private final TaskResultRepository<R> taskResultRepository;
    private final JobExecutor<J, R> jobExecutor;

    @Value("${mapreduce.job.name}")
    private String jobName;

    public JobExecutorService(
            JobRepository<J, T> jobRepository,
            TaskResultRepository<R> taskResultRepository,
            JobExecutor<J, R> jobExecutor
    ) {
        this.jobRepository = jobRepository;
        this.taskResultRepository = taskResultRepository;
        this.jobExecutor = jobExecutor;
    }

    @JmsListener(
            destination = "${mapreduce.queue.job-ready-to-complete.name}",
            containerFactory = "${mapreduce.queue.listener.container-factory.name}"
    )
    public void process(JobParams jobParams) {
        String jobId = jobParams.getJobId();
        log.info("Starting executing job {}, job ID {}", jobName, jobId);
        J job = jobRepository.getJob(jobId);
        try {
            List<R> results = taskResultRepository.findAll(job.getId());
            jobExecutor.execute(job, results);
            job.complete();
            taskResultRepository.deleteAll(jobId);
        } catch (Exception e) {
            job.fail(e);
        }
        jobRepository.updateJobStatus(job);
        log.info("Finished executing job {}, job ID {}", jobName, jobId);
    }
}