package com.productos.inventario.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    private final String SECRET="gatito7465836582687hgdsjhgjgfsjgdhf92837349279bvxbvjhbdsvsbj75472bvshb";
    private final long EXPIRATION_TIME=1000*60*60;//1 HR
    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
       return Jwts.builder()
        .subject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        
    }

    public String extractUsername(String token){
      Claims body=extractClaims(token);
      return body.getSubject();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
       
      return   username.equals(userDetails.getUsername()) && !isTokenExpire(token);
       

    }

    public boolean isTokenExpire(String token){
        Claims body=extractClaims(token);
        return body.getExpiration().before(new Date());
    }


    
}

