package io.github.Cristian3116.jobtrackr.service;

import io.github.Cristian3116.jobtrackr.model.Job;
import io.github.Cristian3116.jobtrackr.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository repo;

    public List<Job> findAll() {
        return repo.findAll();
    }

    public Job save(Job job) {
        return repo.save(job);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Job findById(Long id) {
        return repo.findById(id).orElseThrow();
    }
}