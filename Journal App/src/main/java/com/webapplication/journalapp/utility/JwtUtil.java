package com.webapplication.journalapp.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {
 private String Secrect_key="";
 private SecretKey getSigningKey(){
     return Keys.hmacShaKeyFor(Secrect_key.getBytes());
 }
    public String generateToken(String username){
        HashMap<String ,Object> claims=new HashMap<>();
        return createToken(claims,username);
    }
    private boolean isExpired(String token){
        Date exp=extractExpiration(token);
        return exp.before(new Date(System.currentTimeMillis()));
    }
    public boolean isValid(String token){
        return !isExpired(token);
    }
    private Claims extractAllClaims(String token){
     return Jwts.parser()
             .verifyWith(getSigningKey())
             .build()
             .parseSignedClaims(token)
             .getPayload();
    }
    private Date extractExpiration(String token){
     Claims claims=extractAllClaims(token);
     return claims.getExpiration();
    }
    public String extractUserName(String token){
     Claims claims =extractAllClaims(token);
     return claims.getSubject();
    }
    public String createToken(HashMap<String , Object> claims,String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSigningKey())
                .compact();
    }

}
