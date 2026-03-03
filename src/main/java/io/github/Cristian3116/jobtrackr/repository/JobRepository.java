package io.github.Cristian3116.jobtrackr.repository;

import io.github.Cristian3116.jobtrackr.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}