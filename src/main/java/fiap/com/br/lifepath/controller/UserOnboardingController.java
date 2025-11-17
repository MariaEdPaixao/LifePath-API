package fiap.com.br.lifepath.controller;

import fiap.com.br.lifepath.model.DTO.CreateOnboardingRequest;
import fiap.com.br.lifepath.model.DTO.OnboardingCreatedResponse;
import fiap.com.br.lifepath.model.User;
import fiap.com.br.lifepath.service.UserOnboardingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboarding")
public class UserOnboardingController {
    private final UserOnboardingService onboardingService;

    public UserOnboardingController(UserOnboardingService onboardingService){
        this.onboardingService = onboardingService;
    }

    @PostMapping
    public ResponseEntity<OnboardingCreatedResponse> startOnboarding(@Valid @RequestBody CreateOnboardingRequest request, @AuthenticationPrincipal User user){
        Integer userId = user.getId();
        // Chama o service
        OnboardingCreatedResponse response = onboardingService.createOnboarding(request, userId);

        // Retorna 202 Accepted (pois ainda vai processar com IA)
        return ResponseEntity.accepted().body(response);
    }

}
