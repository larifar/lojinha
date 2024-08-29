package com.larissa.virtual.lojinha.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.larissa.virtual.lojinha.ApplicationContextLoad;
import com.larissa.virtual.lojinha.model.User;
import com.larissa.virtual.lojinha.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@Component
public class JWTTokenAutenticationService {
    private final static long EXPIRATION_TIME = 864000000;
    private final static String SECRET = "chave secreta";
    private final static String PREFIX_TOKEN = "Bearer";
    private final static String HEADER_TOKEN = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws Exception {
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
            enableCors(response);
            response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
        } catch (JWTCreationException exception) {
            response.getWriter().write(exception.getMessage());
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(HEADER_TOKEN);
        try {
            if (token != null) {
                token = token.replace(PREFIX_TOKEN, "").trim();
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("auth0").build();

                String user = jwtVerifier.verify(token).getSubject();
                if (user != null) {
                    User userFind = ApplicationContextLoad
                            .getApplicationContext()
                            .getBean(UserRepository.class).findUserByEmail(user);

                    if (userFind != null) {
                        return new UsernamePasswordAuthenticationToken(
                                userFind.getUsername(), userFind.getPassword(), userFind.getAuthorities());
                    }
                }
            }
        } catch (JWTVerificationException | ExpiredJwtException exception) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
            response.getWriter().write("Token inválido");
            response.getWriter().flush();
            response.getWriter().close();
        } finally {
            enableCors(response);
        }
        return null;
    }

    private void enableCors(HttpServletResponse response) {
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }
        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }

    }
}
