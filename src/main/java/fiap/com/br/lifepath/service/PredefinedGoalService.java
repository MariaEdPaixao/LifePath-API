package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.PredefinedGoal;

import java.util.List;
import java.util.Optional;

public interface PredefinedGoalService {
    List<PredefinedGoal> getAll();
    Optional<PredefinedGoal> findById(Integer id);
}
