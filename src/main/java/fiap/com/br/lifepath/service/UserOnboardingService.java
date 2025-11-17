package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.DTO.CreateOnboardingRequest;
import fiap.com.br.lifepath.model.DTO.OnboardingCreatedResponse;
import fiap.com.br.lifepath.model.UserOnboarding;

import java.util.Optional;

public interface UserOnboardingService {

    Optional<UserOnboarding> findById(Integer id);
    OnboardingCreatedResponse createOnboarding(CreateOnboardingRequest  request, Integer userId);
}
