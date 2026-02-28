package io.github.Cristian3116.jobtrackr.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String position;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate appliedDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public enum Status {
        APPLIED,
        INTERVIEW,
        OFFER,
        REJECTED
    }
}
