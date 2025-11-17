package fiap.com.br.lifepath.service;

import fiap.com.br.lifepath.model.User;
import fiap.com.br.lifepath.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User usuario) {
        return userRepository.save(usuario);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
}
