package com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity;

import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "task", schema = "xml_load_workflow")
public class XmlLoadTaskData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "job_id", referencedColumnName = "id", updatable = false, nullable = false)
    @ManyToOne(fetch = LAZY)
    private XmlLoadJobData job;

    @Column(name = "file_path", updatable = false)
    private String filePath;

    @Column(name = "status")
    private String status;

    @Column(name = "error")
    private String error;
}
