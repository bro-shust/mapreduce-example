package com.broshust.mapreduce.mapreduceexample.workflow.persistence.repository;

import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.ResultRecordData;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ResultRecordDataRepository extends JpaRepository<ResultRecordData, Long>{

    Collection<ResultRecordData> findAllByJobId(String jobId);

    @Modifying
    void deleteAllByJobId(String jobId);
}
