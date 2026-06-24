package unisinos.apptrip.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import unisinos.apptrip.model.User;
import unisinos.apptrip.model.UserRole;
import unisinos.apptrip.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminBootstrap implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${apptrip.admin.name:}")
    private String name;
    @Value("${apptrip.admin.email:}")
    private String email;
    @Value("${apptrip.admin.password:}")
    private String password;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            return;
        }
        var admin = userRepository.findByEmail(email.trim().toLowerCase())
                .orElseGet(() -> User.builder()
                        .name(name == null || name.isBlank() ?"Administrador" : name.trim())
                        .email(email.trim().toLowerCase())
                        .password(passwordEncoder.encode(password))
                        .build());
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);
    }
}
