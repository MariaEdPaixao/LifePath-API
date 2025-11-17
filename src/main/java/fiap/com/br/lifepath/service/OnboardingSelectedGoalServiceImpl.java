package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.OnboardingSelectedGoal;
import fiap.com.br.lifepath.repository.OnboardingSelectedGoalRepository;
import org.springframework.stereotype.Service;

@Service
public class OnboardingSelectedGoalServiceImpl implements OnboardingSelectedGoalService {
    private final OnboardingSelectedGoalRepository onboardingSelectedGoalRepository;

    public OnboardingSelectedGoalServiceImpl(OnboardingSelectedGoalRepository onboardingSelectedGoalRepository){
        this.onboardingSelectedGoalRepository = onboardingSelectedGoalRepository;
    }
    @Override
    public OnboardingSelectedGoal saveUserOnboardingSelectedGoal(OnboardingSelectedGoal userOnboardingSelectedGoal) {
        return onboardingSelectedGoalRepository.save(userOnboardingSelectedGoal);
    }
}
