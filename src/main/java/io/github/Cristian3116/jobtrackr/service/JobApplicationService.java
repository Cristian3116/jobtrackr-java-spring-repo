package io.github.Cristian3116.jobtrackr.service;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.User;
import io.github.Cristian3116.jobtrackr.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<User> user = userRepository.findByUsername(username);
        Map<String, Long> stats = new HashMap<>();

        stats.put("total", repository.countByUser(user.orElse(null)));
        stats.put("pending", repository.countByUserAndStatus(user.orElse(null), "PENDING"));
        stats.put("interview", repository.countByUserAndStatus(user.orElse(null), "INTERVIEW"));
        stats.put("rejected", repository.countByUserAndStatus(user.orElse(null), "REJECTED"));

        return stats;
    }

}