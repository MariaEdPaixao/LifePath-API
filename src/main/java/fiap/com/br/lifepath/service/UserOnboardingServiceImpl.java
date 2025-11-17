package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.DTO.CreateOnboardingRequest;
import fiap.com.br.lifepath.model.DTO.OnboardingCreatedResponse;
import fiap.com.br.lifepath.model.OnboardingSelectedGoal;
import fiap.com.br.lifepath.model.PredefinedGoal;
import fiap.com.br.lifepath.model.User;
import fiap.com.br.lifepath.model.UserOnboarding;
import fiap.com.br.lifepath.model.enums.Priority;
import fiap.com.br.lifepath.model.enums.Status;
import fiap.com.br.lifepath.repository.UserOnboardingRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserOnboardingServiceImpl implements UserOnboardingService {

    private final UserService userService;
    private final UserOnboardingRepository onboardingRepository;
    private final PredefinedGoalService predefinedGoalService;
    private final OnboardingSelectedGoalService onboardingSelectedGoalService;
    private final RabbitTemplate rabbitTemplate;


    public UserOnboardingServiceImpl(UserService userService, UserOnboardingRepository onboardingRepository, PredefinedGoalService predefinedGoalService, OnboardingSelectedGoalService onboardingSelectedGoalService, RabbitTemplate rabbitTemplate){
        this.userService = userService;
        this.predefinedGoalService = predefinedGoalService;
        this.onboardingSelectedGoalService = onboardingSelectedGoalService;
        this.onboardingRepository = onboardingRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Optional<UserOnboarding> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public OnboardingCreatedResponse createOnboarding(CreateOnboardingRequest request, Integer userId) {

        User user = getUser(userId);

        UserOnboarding onboarding = createOnboardingEntity(request, user);

        saveSelectedGoals(request, onboarding);

        publishEvent(onboarding);

        return OnboardingCreatedResponse.builder()
                .onboardingId(onboarding.getId())
                .status(onboarding.getStatus().name())
                .build();
    }

    private User getUser(Integer userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private UserOnboarding createOnboardingEntity(CreateOnboardingRequest request, User user) {
        UserOnboarding onboarding = UserOnboarding.builder()
                .user(user)
                .details(request.getDetails())
                .priority(Priority.valueOf(request.getPriority()))
                .status(Status.PENDENTE)
                .build();

        return onboardingRepository.save(onboarding);
    }

    private void saveSelectedGoals(CreateOnboardingRequest request, UserOnboarding onboarding) {
        for (Integer goalId : request.getSelectedGoals()) {

            PredefinedGoal goal = predefinedGoalService.findById(goalId)
                    .orElseThrow(() -> new RuntimeException("Objetivo não encontrado: " + goalId));

            OnboardingSelectedGoal selectedGoal = OnboardingSelectedGoal.builder()
                    .userOnboarding(onboarding)
                    .predefinedGoal(goal)
                    .build();

            onboardingSelectedGoalService.saveUserOnboardingSelectedGoal(selectedGoal);
        }
    }

    private void publishEvent(UserOnboarding onboarding) {
//        rabbitTemplate.convertAndSend("onboarding.queue", onboarding.getId());
    }
}