package io.github.Cristian3116.jobtrackr.service;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.JobStatus;
import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobApplicationService {


    private final JobApplicationRepository repository;
    private final io.github.Cristian3116.jobtrackr.repository.UserRepository userRepository;

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

    public Map<String, Long> getStatsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        Map<String, Long> stats = new HashMap<>();

        stats.put("total", repository.countByUser(user));


        stats.put("pending", repository.countByUserAndStatus(user, JobStatus.APPLIED));
        stats.put("interview", repository.countByUserAndStatus(user, JobStatus.INTERVIEW));
        stats.put("rejected", repository.countByUserAndStatus(user, JobStatus.REJECTED));
        stats.put("offers", repository.countByUserAndStatus(user, JobStatus.OFFER));

        return stats;
    }
}