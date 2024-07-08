package com.broshust.mapreduce.mapreduceexample.workflow.persistence.repository;

import com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity.XmlLoadTaskData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface XmlLoadTaskDataRepository extends JpaRepository<XmlLoadTaskData, Long> {

    @Modifying
    @Query("update XmlLoadTaskData w set w.status = :status, w.error = :error where w.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") String status, @Param("error") String error);
}
