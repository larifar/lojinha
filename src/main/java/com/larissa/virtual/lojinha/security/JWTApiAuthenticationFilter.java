package com.larissa.virtual.lojinha.security;

import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTApiAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Authentication authentication = new JWTTokenAutenticationService()
                    .getAuthentication((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception ex){
            ex.printStackTrace();
            servletResponse.getWriter().write("Ocorreu um erro no sistema. Erro: " + ex.getMessage());
        }
    }
}
