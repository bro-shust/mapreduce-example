package com.broshust.mapreduce.mapreduceexample.mapreduce.service;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.JobParams;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class JobTrackerService<J extends Job, T extends Task> {

    private final JobRepository<J, T> jobRepository;
    private final JmsTemplate baseTemplateQueue;

    @Value("${mapreduce.queue.job-ready-to-complete.name}")
    private String jobReadyToCompleteQueue;
    @Value("${mapreduce.job.name}")
    private String jobName;

    public JobTrackerService(
            JobRepository<J, T> jobRepository,
            JmsTemplate baseTemplateQueue
    ) {
        this.jobRepository = jobRepository;
        this.baseTemplateQueue = baseTemplateQueue;
    }

    @SchedulerLock(name = "${mapreduce.scheduler.job-readiness-tracker.name}")
    @Scheduled(cron = "${mapreduce.scheduler.job-readiness-tracker.cron}")
    public void track() {
        List<J> readyJobs = jobRepository.findReadyToCompleteJobs();
        log.info("Found {} ready to complete jobs for job {}", readyJobs.size(), jobName);
        readyJobs.forEach(job -> baseTemplateQueue.convertAndSend(jobReadyToCompleteQueue, JobParams.createFrom(job)));
    }
}