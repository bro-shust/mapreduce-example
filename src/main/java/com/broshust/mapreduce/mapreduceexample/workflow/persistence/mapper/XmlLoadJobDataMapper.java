package com.broshust.mapreduce.mapreduceexample.workflow.persistence.mapper;

import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.XmlLoadJobData;
import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.XmlLoadTaskData;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface XmlLoadJobDataMapper {

    XmlLoadJobData toXmlLoadJobData(XmlLoadJob source);

    XmlLoadJob toXmlLoadJob(XmlLoadJobData source);

    XmlLoadTaskData toXmlLoadTaskData(XmlLoadTask source);

    @Mapping(source = "job.workflowId", target = "workflowId")
    @Mapping(source = "job.id", target = "jobId")
    XmlLoadTask toXmlLoadTask(XmlLoadTaskData source);

    @AfterMapping
    default void linkWorkflowJobDataToTasks(@MappingTarget XmlLoadJobData jobData) {
        jobData.getTasks().forEach(taskData -> taskData.setJob(jobData));
    }
}
