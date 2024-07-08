package com.broshust.mapreduce.mapreduceexample.workflow.domain.factory;

import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob.Status;
import java.time.ZonedDateTime;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class XmlLoadJobFactory {

    private final XmlLoadTaskFactory xmlLoadTaskFactory;

    public XmlLoadJob create(String workflowId, Set<String> workflowFiles) {
        return XmlLoadJob.builder()
                         .workflowId(workflowId)
                         .status(Status.IN_PROGRESS)
                         .startedAt(ZonedDateTime.now())
                         .tasks(xmlLoadTaskFactory.create(workflowFiles, workflowId))
                         .build();
    }
}