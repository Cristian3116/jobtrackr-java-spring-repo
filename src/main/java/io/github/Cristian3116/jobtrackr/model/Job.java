package io.github.Cristian3116.jobtrackr.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private String location;

    @Enumerated(EnumType.STRING)
    private JobStatus status; // APPLIED, INTERVIEW, OFFER, REJECTED

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

