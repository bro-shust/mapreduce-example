package com.broshust.mapreduce.mapreduceexample.workflow.persistence.repository;

import com.broshust.mapreduce.mapreduceexample.mapreduce.core.repository.TaskResultRepository;
import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.ResultRecordData;
import com.broshust.mapreduce.mapreduceexample.workflow.persistence.mapper.ResultRecordMapper;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.ResultRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultRecordRepository implements TaskResultRepository<ResultRecord> {

    private final ResultRecordDataRepository resultRecordDataRepository;
    private final ResultRecordMapper resultRecordMapper;

    @Override
    public void save(String jobId, ResultRecord resultRecord) {
        ResultRecordData data = resultRecordMapper.toResultRecordData(resultRecord, jobId);
        resultRecordDataRepository.save(data);
    }

    @Override
    public List<ResultRecord> findAll(String jobId) {
        return resultRecordDataRepository.findAllByJobId(jobId).stream()
                .map(resultRecordMapper::toResultRecord)
                .toList();
    }

    @Override
    public void deleteAll(String jobId) {
        resultRecordDataRepository.deleteAllByJobId(jobId);
    }
}