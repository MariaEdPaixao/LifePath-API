package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.PredefinedGoal;
import fiap.com.br.lifepath.repository.PredefinedGoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredefinedGoalServiceImpl implements PredefinedGoalService {

    private final PredefinedGoalRepository predefinedGoalRepository;

    public PredefinedGoalServiceImpl(PredefinedGoalRepository predefinedGoalRepository) {
        this.predefinedGoalRepository = predefinedGoalRepository;
    }

    @Override
    public List<PredefinedGoal> getAll() {
        return predefinedGoalRepository.findAll();
    }
}