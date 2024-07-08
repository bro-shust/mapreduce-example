package com.broshust.mapreduce.mapreduceexample.mapreduce.service;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.TaskExecutor;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Job;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.model.Task;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.TaskResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;

@Slf4j
public class TaskExecutorService<J extends Job, T extends Task, R> {

    private final JobRepository<J, T> jobRepository;
    private final TaskExecutor<T, R> taskExecutor;
    private final TaskResultRepository<R> taskResultRepository;

    @Value("${mapreduce.job.name}")
    private String jobName;

    public TaskExecutorService(
            JobRepository<J, T> jobRepository,
            TaskExecutor<T, R> taskExecutor,
            TaskResultRepository<R> taskResultRepository
    ) {
        this.jobRepository = jobRepository;
        this.taskExecutor = taskExecutor;
        this.taskResultRepository = taskResultRepository;
    }

    @JmsListener(
            destination = "${mapreduce.queue.task-initialized.name}",
            containerFactory = "${mapreduce.queue.listener.container-factory.name}"
    )
    public void execute(T task) {
        log.info("Received task: {} for job {}", task.getId(), jobName);
        try {
            R result = taskExecutor.execute(task);
            taskResultRepository.save(task.getJobId(), result);
            task.complete();
        } catch (Exception e) {
            task.fail(e);
            taskExecutor.handleError(task, e);
        }
        jobRepository.updateTaskStatus(task);
        log.info("Finished processing task: {} for job {}", task.getId(), jobName);
    }
}