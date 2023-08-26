package com.project.customer.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// as per madhura ma'am
@Component
@Slf4j
public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private Key key;

    @Value("${SECRET_KEY}")
    private String jwtSecret;
    @Value("${EXP_TIMEOUT}")
    private int jwtExpirationMs;
    @PostConstruct
    public void init(){
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    // will be invoked by Authentication controller) , upon successful
    // authentication
    public String generateJwtToken(Authentication authentication) {
        log.info("generate jwt token " + authentication);
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
//JWT : userName,issued at ,exp date,digital signature(does not typically contain password , can contain authorities
        return Jwts.builder() // JWTs : a Factory class , used to create JWT tokens
                .setSubject((userPrincipal.getUsername())) // setting subject part of the token(typically user
                // name/email)
                .setIssuedAt(new Date())// Sets the JWT Claims iat (issued at) value of current date
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))// Sets the JWT Claims exp
                // (expiration) value.
                //adding custom claim of user id
                .claim("user_id", userPrincipal.getUser().getId().toString())
                .signWith(key, SignatureAlgorithm.HS512) // Signs the constructed JWT using the specified
                // algorithm with the specified key, producing a
                // JWS(Json web signature=signed JWT)

                // Using token signing algo : HMAC using SHA-512
                .compact();// Actually builds the JWT and serializes it to a compact, URL-safe string
    }

    // this method will be invoked by our custom JWT filter
    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    // this method will be invoked by our custom filter
    public Claims validateJwtToken(String jwtToken) {
        // try {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().
                // Sets the signing key used to verify JWT digital signature.
                        parseClaimsJws(jwtToken).getBody();// Parses the signed JWT returns the resulting Jws<Claims> instance
        // throws exc in case of failures in verification
        return claims;
    }
    // Accepts Collection<GrantedAuthority> n rets comma separated list of it's
    // string form

    private String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        String authorityString = authorities.stream().
                map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        System.out.println(authorityString);
        return authorityString;
    }

    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        String authString = (String) claims.get("authorities");
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
        authorities.forEach(System.out::println);
        return authorities;
    }
    //get user id from claims
    public Long getUserIdFromClaims(Claims claims) {
        String userId = (String) claims.get("user_id");
        return Long.parseLong(userId);
    }
}
