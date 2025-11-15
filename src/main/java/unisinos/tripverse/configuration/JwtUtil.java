package unisinos.tripverse.configuration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.secret-key}")
    private String secretKey;

    private static final long EXPIRATION_MS = 1000 * 60 * 60;

    private JwtParser getJwtParser(){

        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(this.getSigningKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        return getJwtParser().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValid(String token) {
        try {
            getJwtParser().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
