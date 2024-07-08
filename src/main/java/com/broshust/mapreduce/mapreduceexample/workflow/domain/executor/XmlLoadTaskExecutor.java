package com.broshust.mapreduce.mapreduceexample.workflow.domain.executor;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.executor.TaskExecutor;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.ResultRecord;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.XmlLoadTask;
import org.springframework.stereotype.Component;

@Component
public class XmlLoadTaskExecutor implements TaskExecutor<XmlLoadTask, ResultRecord> {

    @Override
    public ResultRecord execute(XmlLoadTask task) {
        //map function, this part will be executed distributed
        //some business logic here
        return new ResultRecord(task.getFilePath());
    }

    @Override
    public void handleError(XmlLoadTask task, Exception e) {
        //some error handling
    }
}
