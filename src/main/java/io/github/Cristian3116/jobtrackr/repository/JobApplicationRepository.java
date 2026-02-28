package io.github.Cristian3116.jobtrackr.repository;

import io.github.Cristian3116.jobtrackr.model.JobApplication;
import io.github.Cristian3116.jobtrackr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);
}
