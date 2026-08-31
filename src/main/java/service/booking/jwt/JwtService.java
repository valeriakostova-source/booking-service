package service.booking.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public Long extractCustomerId(String token) {
        String customerId = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id").toString();

        return Long.parseLong(customerId);


    }

    public boolean isTokenValid(String token) {
        try {
            extractCustomerId(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private SecretKey getSignInKey() {
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }
}
