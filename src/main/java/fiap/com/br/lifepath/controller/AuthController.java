package fiap.com.br.lifepath.controller;

import fiap.com.br.lifepath.model.DTO.UserRegisterDTO;
import fiap.com.br.lifepath.model.DTO.UserResponseDTO;
import fiap.com.br.lifepath.model.Token;
import fiap.com.br.lifepath.model.DTO.Credentials;
import fiap.com.br.lifepath.model.User;
import fiap.com.br.lifepath.service.TokenService;
import fiap.com.br.lifepath.service.UserService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @CacheEvict(value = "usuarios", allEntries = true)
    public ResponseEntity<Token> login(@RequestBody Credentials credentials) {
        try {
            var auth = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
            var user = (User) authenticationManager.authenticate(auth).getPrincipal();

            Token token = tokenService.createToken(user);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos!");
        }
    }

    @PostMapping("/register")
    @CacheEvict(value = "usuarios", allEntries = true)
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO dto) {

        if (userService.findByEmail(dto.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já cadastrado");
        }

        User newUser = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        newUser = userService.saveUser(newUser);

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

        var auth = authenticationManager.authenticate(authToken);
        var loggedUser = (User) auth.getPrincipal();

        Token token = tokenService.createToken(loggedUser);

        UserResponseDTO response = new UserResponseDTO(
                loggedUser.getId(),
                loggedUser.getName(),
                loggedUser.getEmail(),
                token.token()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
