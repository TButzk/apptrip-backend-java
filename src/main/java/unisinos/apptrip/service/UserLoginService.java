package unisinos.apptrip.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unisinos.apptrip.dto.AuthenticationDto;
import unisinos.apptrip.dto.CreateUserDto;
import unisinos.apptrip.model.result.LoginResult;
import unisinos.apptrip.model.User;
import unisinos.apptrip.util.JwtUtil;
import unisinos.apptrip.exception.ApiException;
import org.springframework.http.HttpStatus;
import unisinos.apptrip.repository.UserRepository;

@Service
@Slf4j
public class UserLoginService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserDto dto) {
        log.debug("Saving user: {}", dto.getEmail());

        var email = dto.getEmail().trim().toLowerCase();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT, "Já existe uma conta com este e-mail.");
        }
        var user = User.builder().email(email).name(dto.getName().trim()).build();

        user.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }

    public LoginResult login(AuthenticationDto authRequest) {
        var email = authRequest.getEmail().trim().toLowerCase();
        log.info("Authenticating user: {}", email);

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, authRequest.getPassword())
        );

        var user = userRepository.findByEmail(email).orElseThrow();

        var token = jwtUtil.generateToken(user);

        return new LoginResult(user, token);
    }
}

