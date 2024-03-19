package org.romanzhula.bookstore.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> function) {
        var claims = extractAllClaims(token);
        return function.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        var issuedAt = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        var expiration = issuedAt.plus(2, ChronoUnit.HOURS);

        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(issuedAt)) //time now
                .expiration(Date.from(expiration)) //only 2 hours
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var usernameFromToken = extractUsernameFromToken(token);

        return usernameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("WzBZlEhw7iAX8Xe31wU4mzhrLWoSZL92JMWP+0CwLMm0Wtdkcye1zzH9Tp9a+nLwvAfLmZiVwt+k+Y3RObLrAA==\n".getBytes());
    }
}
