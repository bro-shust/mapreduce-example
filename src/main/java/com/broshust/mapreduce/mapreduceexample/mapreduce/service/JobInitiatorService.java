package com.broshust.mapreduce.mapreduceexample.mapreduce.service;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class JobInitiatorService<J extends Job, T extends Task> {

    private final JobRepository<J, T> jobRepository;
    private final JmsTemplate jsonTemplateQueue;

    @Value("${mapreduce.queue.task-initialized.name}")
    private String taskInitializedQueue;
    @Value("${mapreduce.job.name}")
    private String jobName;

    public JobInitiatorService(
            JobRepository<J, T> jobRepository,
            JmsTemplate jsonTemplateQueue
    ) {
        this.jobRepository = jobRepository;
        this.jsonTemplateQueue = jsonTemplateQueue;
    }

    public void execute(J job) {
        log.info("Running Job {}, job ID: {}", jobName, job.getId());
        jobRepository.saveJob(job)
                     .getTasks()
                     .forEach(task -> jsonTemplateQueue.convertAndSend(taskInitializedQueue, task));
    }
}