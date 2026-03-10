package io.github.Cristian3116.jobtrackr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username-ul este obligatoriu")
    @Size(min = 4, max = 20, message = "Username-ul trebuie să aibă între 4 și 20 de caractere")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Parola este obligatorie")
    @Size(min = 6, message = "Parola trebuie să aibă cel puțin 6 caractere")
    private String password;

    private String role; // "USER" sau "ADMIN"

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<JobApplication> jobs;
}