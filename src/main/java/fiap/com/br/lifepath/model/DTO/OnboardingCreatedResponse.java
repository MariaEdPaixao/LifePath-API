package fiap.com.br.lifepath.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OnboardingCreatedResponse {
    private Integer onboardingId;
    private String status;
}
