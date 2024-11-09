package br.ufsm.redescomp.nutrigest.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${api.secret}")
    private String API_SECRET;

    private static final Instant EXPIRATION_TIME = LocalDateTime.now()
            .plusHours(2)
            .toInstant(ZoneOffset.of("-03:00"));

    public String gerarToken(User user) {

        // TODO: Lançar JWTCreationException
        return JWT.create()
                .withIssuer("nutrigest-api")
                .withSubject(user.getUsername())
                .withClaim("role", user.getAuthorities().stream().toList().getFirst().toString())
                .withExpiresAt(EXPIRATION_TIME)
                .sign(Algorithm.HMAC256(API_SECRET));
    }

    public String getSubject(String jwt) {
        return JWT.decode(jwt).getSubject();
    }

    public String validarToken(String jwt) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC256(API_SECRET))
                .build()
                .verify(jwt)
                .getToken();
    }

}
