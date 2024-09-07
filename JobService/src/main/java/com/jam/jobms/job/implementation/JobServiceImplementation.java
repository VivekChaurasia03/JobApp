package com.jam.jobms.job.implementation;

import com.jam.jobms.job.Job;
import com.jam.jobms.job.JobRepository;
import com.jam.jobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobServiceImplementation implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    // private Long nextId = 1L;
    JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        /*
        for(Job job: jobs) {
            if(job.getId().equals(id)) {
                return job;
            }
        }
         */
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        /*
        // Another way to iterate and delete the job
        Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()) {
            Job job = iterator.next();
            if(job.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }

        return jobs.removeIf(job -> Objects.equals(job.getId(), id));
        */
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Job updateJobById(Long id, Job updatedJobDetails) {
        /*
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                updateOldJob(updatedJobDetails, job);
                return job;
            }

        }
        return null;
         */
        Optional<Job> jobFound = jobRepository.findById(id);
        if(jobFound.isPresent()) {
            Job job = jobFound.get();
            updateOldJob(updatedJobDetails, job);
            return jobRepository.save(job);
        }
        return null;
    }

    private static void updateOldJob(Job updatedJobDetails, Job job) {
        job.setTitle(updatedJobDetails.getTitle());
        job.setDescription(updatedJobDetails.getDescription());
        job.setMinSalary(updatedJobDetails.getMinSalary());
        job.setMaxSalary(updatedJobDetails.getMaxSalary());
        job.setSalary(updatedJobDetails.getSalary());
        job.setLocation(updatedJobDetails.getLocation());

    }
}
