package com.broshust.mapreduce.mapreduceexample.workflow.config;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.JobExecutor;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.TaskExecutor;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.TaskResultRepository;
import com.broshust.mapreduce.mapreduceexample.mapreduce.service.JobExecutorService;
import com.broshust.mapreduce.mapreduceexample.mapreduce.service.JobInitiatorService;
import com.broshust.mapreduce.mapreduceexample.mapreduce.service.JobTrackerService;
import com.broshust.mapreduce.mapreduceexample.mapreduce.service.TaskExecutorService;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.ResultRecord;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ConditionalOnProperty(prefix = "mapreduce", name = "enabled", havingValue = "true", matchIfMissing = true)
public class XmlLoadMapReduceConfiguration {

    @Bean
    public JobInitiatorService<XmlLoadJob, XmlLoadTask> xmlLoadJobInitiatorService(
            JobRepository<XmlLoadJob, XmlLoadTask> jobRepository,
            JmsTemplate jsonTemplateQueue
    ) {
        return new JobInitiatorService<>(jobRepository, jsonTemplateQueue);
    }

    @Bean
    public TaskExecutorService<XmlLoadJob, XmlLoadTask, ResultRecord> xmlLoadTaskExecutorService(
            JobRepository<XmlLoadJob, XmlLoadTask> jobRepository,
            TaskExecutor<XmlLoadTask, ResultRecord> taskExecutor,
            TaskResultRepository<ResultRecord> taskResultRepository
    ) {
        return new TaskExecutorService<>(jobRepository, taskExecutor, taskResultRepository);
    }

    @Bean
    public JobExecutorService<XmlLoadJob, XmlLoadTask, ResultRecord> xmlLoadJobExecutorService(
            JobRepository<XmlLoadJob, XmlLoadTask> jobRepository,
            TaskResultRepository<ResultRecord> taskResultRepository,
            JobExecutor<XmlLoadJob, ResultRecord> jobExecutor
    ) {
        return new JobExecutorService<>(jobRepository, taskResultRepository, jobExecutor);
    }

    @Bean
    public JobTrackerService<XmlLoadJob, XmlLoadTask> xmlLoadJobTrackerService(
            JobRepository<XmlLoadJob, XmlLoadTask> jobRepository,
            JmsTemplate jsonTemplateQueue
    ) {
        return new JobTrackerService<>(jobRepository, jsonTemplateQueue);
    }
}