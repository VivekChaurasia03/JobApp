package com.jam.jobms.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    Job updateJobById(Long id, Job updatedJobDetails);
}
