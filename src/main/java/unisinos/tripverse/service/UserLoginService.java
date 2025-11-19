package unisinos.tripverse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import unisinos.tripverse.configuration.JwtUtil;
import unisinos.tripverse.configuration.UserProvider;
import unisinos.tripverse.exception.AlreadyExistsException;
import unisinos.tripverse.model.user.*;
import unisinos.tripverse.repository.UserRepository;

@Service
@Slf4j
public class UserLoginService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserDto dto) throws AlreadyExistsException {
        log.debug("Saving user: {}", dto.getEmail());

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new AlreadyExistsException(dto.getEmail());
        }
        var user = User.builder().email(dto.getEmail()).name(dto.getName()).build();

        user.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    public LoginResult login(AuthenticationDto authRequest) {
        log.info("Authenticating user: {}", authRequest.getEmail());

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();

        var token = jwtUtil.generateToken(user);

        return new LoginResult(user, token);
    }
}
