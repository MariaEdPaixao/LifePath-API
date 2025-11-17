package fiap.com.br.lifepath.model;

import fiap.com.br.lifepath.model.enums.Priority;
import fiap.com.br.lifepath.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user_onboarding")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOnboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_onboarding")
    private Integer id;

    @Size(max = 250)
    private String details;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
