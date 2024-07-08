package com.broshust.mapreduce.mapreduceexample.workflow.api;

import com.broshust.mapreduce.mapreduceexample.workflow.api.model.request.WorkflowParamsRequest;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkflowApiController implements WorkflowApi {

    private final WorkflowService workflowService;

    @Override
    public void startWorkflow(WorkflowParamsRequest request) {
        workflowService.startWorkflow(request.getWorkflowId());
    }
}