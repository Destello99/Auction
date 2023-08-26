package com.project.customer.Security;

import com.project.customer.dto.JwtAuthRequest;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // check for authorization hdr
        String authHeadr = request.getHeader("Authorization");
        if (authHeadr != null && authHeadr.startsWith("Bearer")) {
            System.out.println("got bearer token");
            String token = authHeadr.substring(7);
            Claims claims = jwtTokenHelper.validateJwtToken(token);
            // extract subject from the token
            String email = jwtTokenHelper.getUserNameFromJwtToken(claims);
            // extract authorities from the token
            List<GrantedAuthority> authorities = jwtTokenHelper.getAuthoritiesFromClaims(claims);
            //extract user id from the token
            //	System.out.println("user id from jwt "+utils.getUserIdFromClaims(claims));
            // wrap user details (username/email +id : UserDetailsDTO +granted authorities ) in the
            // username pwd auth token
            JwtAuthRequest dto=new JwtAuthRequest(email, jwtTokenHelper.getUserIdFromClaims(claims));
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(dto, null,
                            authorities);
            //save above auth object in the spring sec ctx
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else
            System.out.println("req did not contain any bearer token");
        filterChain.doFilter(request, response);// passing the control to the nexyt filter in the chain

    }
}
