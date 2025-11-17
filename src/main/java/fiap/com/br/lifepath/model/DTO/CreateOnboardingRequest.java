package fiap.com.br.lifepath.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOnboardingRequest {

    @NotEmpty(message = "Você deve selecionar pelo menos um objetivo.")
    private List<Integer> selectedGoals;

    @NotNull(message = "A prioridade é obrigatória.")
    private String priority;

    private String details;
}
