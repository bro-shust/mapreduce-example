package com.broshust.mapreduce.mapreduceexample.workflow.persistence.mapper;

import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.ResultRecordData;
import com.broshust.mapreduce.mapreduceexample.workflow.domain.model.ResultRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResultRecordMapper {

    ResultRecord toResultRecord(ResultRecordData source);

    @Mapping(source = "jobId", target = "jobId")
    ResultRecordData toResultRecordData(ResultRecord source, String jobId);
}
