package fiap.com.br.lifepath.model.DTO;

public record UserResponseDTO(
        Integer id,
        String name,
        String email,
        String token
) {}
