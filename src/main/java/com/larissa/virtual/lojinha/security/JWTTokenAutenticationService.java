package com.larissa.virtual.lojinha.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAKey;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticationService {
    private final static long EXPIRATION_TIME = 864000000;
    private final static String SECRET = "chave secreta";
    private final static String PREFIX_TOKEN = "Bearer";
    private final static String HEADER_TOKEN = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws Exception{
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String jwt = JWT.create()
                    .withSubject(username)
                    .withIssuer("auth0")
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(algorithm);
            token = PREFIX_TOKEN + " " + jwt;
            response.addHeader(HEADER_TOKEN, token);
            response.getWriter().write("{\"Authorization\": \""+token+"\"}");
        } catch (JWTCreationException exception){
            throw new Exception(exception);
        }

    }
}
