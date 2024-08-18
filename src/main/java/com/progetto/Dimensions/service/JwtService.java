package com.progetto.Dimensions.service;

import com.progetto.Dimensions.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.application.key}")
    private String key;

    @Value("${jwt.application.jwt_expiration}")
    private long jwt_expiration;


    public String generateToken (User user){
        Map<String, Object> extra = new HashMap<>();
        extra.put("mail",user.getMail());
        extra.put("id",user.getId());
        return generateToken(user,extra);
    }

    public String generateToken (User user, Map<String, Object> extra){
        return builToken(user, extra, jwt_expiration);
    }

    public String builToken (User user, Map<String, Object> extra, long expiration){
        return Jwts.builder()
                .setClaims(extra)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    public Key getKey (){
        byte [] jbytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(jbytes);
    }

    public String extractUsername (String token){
    return extractClaim(token, Claims::getSubject);
    }

    public String extractMail (String token){
    Claims claims = extractAll(token);
        return claims.get("mail").toString();
    }

    public Long extractUserID (String token) {
        Claims claims = extractAll(token);
        Object id = claims.get("id");
        if(id instanceof Integer){
            return ((Integer)id).longValue();
        }
        return (Long)id;
    }

    public <T> T extractClaim (String token, Function<Claims,T> resolver){
        Claims claims = extractAll(token);
        return resolver.apply(claims);
    }

    public Claims extractAll (String token){
    byte [] jbytes = Decoders.BASE64.decode(key);
    SecretKey secretKey = Keys.hmacShaKeyFor(jbytes);
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public Date extractExpiration (String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid (String token, UserDetails userDetails){
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())&& !isExpired(token);
    }

    public boolean isExpired (String token){
        return extractExpiration(token).before(new Date());
    }










    }
