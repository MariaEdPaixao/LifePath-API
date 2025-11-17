package fiap.com.br.lifepath.repository;

import fiap.com.br.lifepath.model.PredefinedGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PredefinedGoalRepository extends JpaRepository<PredefinedGoal, Integer> {
    Optional<PredefinedGoal> findById(Integer id);
}
