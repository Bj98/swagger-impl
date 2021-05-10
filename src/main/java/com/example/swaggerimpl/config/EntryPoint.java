package com.example.swaggerimpl.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EntryPoint implements AuthenticationEntryPoint {

    public static final String AUTHENTICATE_HEADER_VALUE="Basic realm=\"Login to OpenAPI 3.0\"";
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.addHeader("WWW-Authenticate",AUTHENTICATE_HEADER_VALUE);
        httpServletResponse.setStatus(401);
        PrintWriter writer=httpServletResponse.getWriter();
        writer.println("Access Denied");

    }
}
