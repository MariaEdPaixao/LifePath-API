package fiap.com.br.lifepath.repository;

import fiap.com.br.lifepath.model.UserOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOnboardingRepository extends JpaRepository<UserOnboarding, Integer> {
    Optional<UserOnboarding> findById(Integer id);
}

