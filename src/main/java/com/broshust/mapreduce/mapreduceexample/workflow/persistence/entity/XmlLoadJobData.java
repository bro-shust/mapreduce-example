package com.broshust.mapreduce.mapreduceexample.workflow.persistence.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job", schema = "xml_load_workflow")
public class XmlLoadJobData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "workflow_id", updatable = false)
    private String workflowId;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "job")
    private Set<XmlLoadTaskData> tasks = new HashSet<>();

    @Column(name = "started_at", updatable = false)
    private ZonedDateTime startedAt;

    @Column(name = "finished_at")
    private ZonedDateTime finishedAt;

    @Column(name = "error")
    private String error;
}
