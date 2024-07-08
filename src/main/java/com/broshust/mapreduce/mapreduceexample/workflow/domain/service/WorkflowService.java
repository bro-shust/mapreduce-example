package com.broshust.mapreduce.mapreduceexample.workflow.domain.service;

import com.broshust.mapreduce.mapreduceexample.mapreduce.service.JobInitiatorService;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.factory.XmlLoadJobFactory;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final JobInitiatorService<XmlLoadJob, XmlLoadTask> jobInitiatorService;
    private final XmlLoadJobFactory xmlLoadJobFactory;

    public void startWorkflow(String workflowId) {
        Set<String> paths = getWorkflowFilePaths();
        XmlLoadJob job = xmlLoadJobFactory.create(workflowId, paths);
        jobInitiatorService.execute(job);
    }

    private Set<String> getWorkflowFilePaths() {
        //todo get workflow file paths from somewhere
        return Set.of("file1", "file2", "file3");
    }
}
