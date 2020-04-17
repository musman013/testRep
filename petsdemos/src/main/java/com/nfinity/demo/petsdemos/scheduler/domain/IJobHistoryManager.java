package com.nfinity.demo.petsdemos.scheduler.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nfinity.demo.petsdemos.scheduler.domain.model.JobHistoryEntity;

import com.querydsl.core.types.Predicate;


public interface IJobHistoryManager {

    // CRUD Operations
    JobHistoryEntity create(JobHistoryEntity job);

    List<JobHistoryEntity> findByJob(String jobName, String jobGroup);

    List<JobHistoryEntity> findByTrigger(String triggerName, String triggerGroup);
    Page<JobHistoryEntity> findAll(Predicate predicate,Pageable pageable);

}
