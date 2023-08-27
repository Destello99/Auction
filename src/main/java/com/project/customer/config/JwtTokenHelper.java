package com.project.customer.config;

import com.project.customer.entity.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private Key key;

    @Value("${SECRET_KEY}")
    private String jwtSecret;

    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    //extracting username from token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //this method extract all claims
    public <T> T getClaimFromToken(String token , Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //adding custom claims in JWT payload
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Integer id = null;
        if (userDetails instanceof Customer) {
            Customer customer = (Customer) userDetails;
            id = customer.getId();
        }
        claims.put("userId", id);
        //can add as many custom claims as want
        return doGenerateToken(claims, userDetails.getUsername());
    }


    private String doGenerateToken(Map<String , Object> claims, String subject){
        return  Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 24 * 3600 * 1000))
                .signWith(key,SignatureAlgorithm.HS256 )
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
