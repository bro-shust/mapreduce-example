package com.broshust.mapreduce.mapreduceexample.workflow.api.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ProblemHandling {

}