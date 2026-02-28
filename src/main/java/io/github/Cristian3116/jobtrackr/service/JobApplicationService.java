package io.github.Cristian3116.jobtrackr.service;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplication save(JobApplication app) {
        return repository.save(app);
    }

    public List<JobApplication> getAllForUser(User user) {
        return repository.findByUser(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public JobApplication getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}