package com.secui.mvc.config;

import com.secui.mvc.utility.ConstantUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.secret}")
    private String apiSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get the API key and secret from request headers
        String requestApiKey = request.getHeader(ConstantUtil.KEY_HEADER);
        String requestApiSecret = request.getHeader(ConstantUtil.SECRET_HEADER);
        // Validate the key and secret
        if (request.getRequestURL().toString().contains("/api/v1/")) {
            if (apiKey.equals(requestApiKey) && apiSecret.equals(requestApiSecret)) {
                // Continue processing the request
                filterChain.doFilter(request, response);
            } else {
                // Reject the request and send an unauthorized error
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
