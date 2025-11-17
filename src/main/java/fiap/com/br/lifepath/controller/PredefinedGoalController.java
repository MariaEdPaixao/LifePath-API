package fiap.com.br.lifepath.controller;

import fiap.com.br.lifepath.model.PredefinedGoal;
import fiap.com.br.lifepath.model.User;
import fiap.com.br.lifepath.service.PredefinedGoalService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class PredefinedGoalController {

    private final PredefinedGoalService predefinedGoalService;

    public PredefinedGoalController(PredefinedGoalService predefinedGoalService) {
        this.predefinedGoalService = predefinedGoalService;
    }

    @GetMapping
    @Cacheable(value = "goalsCache")
    public List<PredefinedGoal> getAllGoals(@AuthenticationPrincipal User user) {
        return predefinedGoalService.getAll();
    }
}
