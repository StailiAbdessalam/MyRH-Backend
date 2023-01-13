package com.veinsmoke.myrhbackend.security;

import com.veinsmoke.myrhbackend.helper.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtils;
    private final UserDetailsService userDetailService;
    private final TokenStore tokenStore;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String userType;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);

        if(tokenStore.getAuthentication(jwtToken) != null){
            filterChain.doFilter(request, response);
            return;
        }

        userEmail = jwtUtils.extractUsername(jwtToken);
        userType = jwtUtils.extractUserType(jwtToken);
        if(userEmail != null && userType != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailService.loadUserByUsername(userEmail+":"+userType);
            if(userDetails != null){
                if(jwtUtils.isTokenValid(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}