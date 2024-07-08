package com.broshust.mapreduce.mapreduceexample.workflow.domain.factory;

import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask.Status;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class XmlLoadTaskFactory {

    public List<XmlLoadTask> create(Set<String> workflowFiles, String workflowId) {
        return workflowFiles.stream()
                            .map(path -> create(path, workflowId))
                            .collect(Collectors.toList());
    }

    private XmlLoadTask create(String filePath, String workflowId) {
        return XmlLoadTask.builder()
                          .workflowId(workflowId)
                          .filePath(filePath)
                          .status(Status.INITIALIZED)
                          .build();
    }
}