package io.github.Cristian3116.jobtrackr.repository;

import io.github.Cristian3116.jobtrackr.model.Job;
import io.github.Cristian3116.jobtrackr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByUser(User user);

}