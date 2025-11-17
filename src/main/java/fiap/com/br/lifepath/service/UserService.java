package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User usuario);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Integer id);
}
