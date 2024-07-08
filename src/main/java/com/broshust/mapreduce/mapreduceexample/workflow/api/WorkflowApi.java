package com.broshust.mapreduce.mapreduceexample.workflow.api;

import com.broshust.mapreduce.mapreduceexample.workflow.api.model.request.WorkflowParamsRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@RequestMapping("/trademark/workflows")
public interface WorkflowApi {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void startWorkflow(@Valid @RequestBody WorkflowParamsRequest request);
}
