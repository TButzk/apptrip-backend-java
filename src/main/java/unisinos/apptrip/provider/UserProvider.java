package unisinos.apptrip.provider;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import unisinos.apptrip.model.AuthenticatedUser;

@Component
public class UserProvider {

    public AuthenticatedUser getAuthenticatedUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("Nenhum usuário autenticado no contexto.");
        }

        Object principal = auth.getPrincipal();

        if (!(principal instanceof AuthenticatedUser)) {
            throw new IllegalStateException("Principal não é uma instância de UserDetails.");
        }

        return ((AuthenticatedUser) principal);
    }
}

