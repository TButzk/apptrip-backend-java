package unisinos.apptrip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.User;
import unisinos.apptrip.model.UserRole;
import unisinos.apptrip.provider.UserProvider;
import unisinos.apptrip.repository.UserRepository;

import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserProvider userProvider;
    private final UserRepository userRepository;

    public User currentUser() {
        var auth = userProvider.getAuthenticatedUser();
        return userRepository.findById(auth.getId())
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Usuário autenticado não existe."));
    }

    public Optional<User> currentUserOptional() {
        return userProvider.getOptionalAuthenticatedUser().flatMap(user -> userRepository.findById(user.getId()));
    }

    public boolean isCurrentUser(UUID userId) {
        return userProvider.getOptionalAuthenticatedUser()
                .map(user -> user.getId().equals(userId))
                .orElse(false);
    }

    public boolean isAdmin() {
        return userProvider.getOptionalAuthenticatedUser()
                .map(user -> user.getRole() == UserRole.ADMIN)
                .orElse(false);
    }

    public void requireOwnerOrAdmin(UUID ownerId, String message) {
        if (!isCurrentUser(ownerId) && !isAdmin()) {
            throw new ApiException(HttpStatus.FORBIDDEN, message);
        }
    }
}
