package com.broshust.mapreduce.mapreduceexample.workflow.domain.executor;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.JobExecutor;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.ResultRecord;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadJob;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class XmlLoadJobExecutor implements JobExecutor<XmlLoadJob, ResultRecord> {

    @Override
    public void execute(XmlLoadJob job, List<ResultRecord> taskResults) {
        //reduce function
        //some business logic here
    }
}
