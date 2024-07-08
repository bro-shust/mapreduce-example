package com.broshust.mapreduce.mapreduceexample.workflow.persistence.repository;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.JobRepository;
import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.XmlLoadJobData;
import com.broshust.mapreduce.mapreduceexample.workflow.persistence.mapper.XmlLoadJobDataMapper;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class XmlLoadJobRepository implements JobRepository<XmlLoadJob, XmlLoadTask> {

    private final XmlLoadJobDataRepository xmlLoadJobDataRepository;
    private final XmlLoadTaskDataRepository xmlLoadTaskDataRepository;
    private final XmlLoadJobDataMapper xmlLoadJobMapper;

    @Override
    public XmlLoadJob saveJob(XmlLoadJob job) {
        XmlLoadJobData data = xmlLoadJobMapper.toXmlLoadJobData(job);
        XmlLoadJobData saved = xmlLoadJobDataRepository.save(data);
        return xmlLoadJobMapper.toXmlLoadJob(saved);
    }

    @Override
    public void updateJobStatus(XmlLoadJob job) {
        Long id = Long.valueOf(job.getId());
        String status = job.getStatus().name();
        ZonedDateTime finishedAt = job.getFinishedAt();
        String error = job.getError();
        xmlLoadJobDataRepository.updateStatus(id, status, finishedAt, error);
    }

    @Override
    public List<XmlLoadJob> findReadyToCompleteJobs() {
        return xmlLoadJobDataRepository.findReadyJobs()
                                       .stream()
                                       .map(xmlLoadJobMapper::toXmlLoadJob)
                                       .toList();
    }

    @Override
    public XmlLoadJob getJob(String jobId) {
        Long id = Long.valueOf(jobId);
        return xmlLoadJobDataRepository.findById(id)
                                       .map(xmlLoadJobMapper::toXmlLoadJob)
                                       .orElseThrow();
    }

    @Override
    public void updateTaskStatus(XmlLoadTask task) {
        Long id = Long.valueOf(task.getId());
        String status = task.getStatus().name();
        String error = task.getError();
        xmlLoadTaskDataRepository.updateStatus(id, status, error);
    }
}
