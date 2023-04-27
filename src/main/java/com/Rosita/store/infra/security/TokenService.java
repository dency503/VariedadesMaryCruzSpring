package com.Rosita.store.infra.security;

import com.Rosita.store.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public  String generarToken(Usuario principal){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String auth0 = JWT.create()
                    .withIssuer("voll med")
                    .withSubject(principal.getUsername())
                    .withClaim("id", principal.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return auth0;
        } catch (
                JWTCreationException exception){
            throw  new RuntimeException();
        }


        }  private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}
