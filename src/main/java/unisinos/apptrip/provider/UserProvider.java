package unisinos.apptrip.provider;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import unisinos.apptrip.exception.ApiException;
import unisinos.apptrip.model.AuthenticatedUser;

import java.util.Optional;

@Component
public class UserProvider {
    public AuthenticatedUser getAuthenticatedUser() {
        return getOptionalAuthenticatedUser()
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Autenticação obrigatória."));
    }

    public Optional<AuthenticatedUser> getOptionalAuthenticatedUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !(auth.getPrincipal() instanceof AuthenticatedUser user)) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
