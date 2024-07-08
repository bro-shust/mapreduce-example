package com.broshust.mapreduce.mapreduceexample.workflow.persistence.repository;

import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.XmlLoadJobData;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface XmlLoadJobDataRepository extends JpaRepository<XmlLoadJobData, Long> {

    @Modifying
    @Query("update XmlLoadJobData j set j.status = :status, j.finishedAt = :finishedAt, j.error = :error where j.id = :id")
    void updateStatus(
            @Param("id") Long id,
            @Param("status") String status,
            @Param("finishedAt") ZonedDateTime finishedAt,
            @Param("error") String error
    );

    @Query("SELECT DISTINCT j FROM XmlLoadJobData j JOIN j.tasks t WHERE j.status = 'IN_PROGRESS' AND t.status IN ('COMPLETED', 'FAILED')")
    List<XmlLoadJobData> findReadyJobs();
}
