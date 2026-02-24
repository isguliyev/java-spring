package com.example.MegaSenaAPI.security;

import com.example.MegaSenaAPI.account.Account;
import com.example.MegaSenaAPI.account.dto.RegisterRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.awt.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.classfile.Attributes.record;

@Component
public class JWTService {
    private final byte[] SECRET_KEY = Decoders.BASE64.decode("c2l0dGluZ3N3ZXB0Y291cmFnZWV4Y2l0ZW1lbnRpbW1lZGlhdGVseWZsb2F0aW5nbGVhZGh1bmdyeXBsZWFzdXJlcXVpY2tseWhpZGRlbmF0ZXBvZXRob3VycGFydHdoZW5zYXZlZGJyaWVmZmlyZXdvcmthY2NvcmRpbmdzcGlkZXJjb2F0ZWxlbWVudGZ1bGx5cmVtb3Zlc3RlYW13aGV0aGVyZHVsbHNlZWxhdWdoYWNjb3JkaW5nZHVnYWZ0ZXJidXR0ZXJkaWdkaXNhcHBlYXJjb3B5enVsdWNvd2JveWxlYXRoZXJtb25leWJlbGlldmVkY2FuYWxwbGFubmluZ3NtYWxsZXN0YXJ0aWNsZXNtb290aHBhcmFncmFwaG9waW5pb25wbGVhc2VsYXl1bml0d2FybXByaW5jaXBsZXRoeWV4Y2VwdGNhbXBhbHRob3VnaHNtYWxsZXJwZXJoYXBzYmx1ZWRvemVucHJpZGVwcm9wZXJ0eXRpbnlleHBlcmltZW50dm9pY2VmdW5ob3VzZWNhcmRtYXR0ZXJhcnRzZWNyZXRiaXRlc29uZ3Nob3BwYXJ0Y2VydGFpbmx5bGlicmFyeXRlbXBlcmF0dXJlYWJsZXNhdGlzZmllZGJ1c2h0cmllZGZhaXJseW5hdHVyYWxoYW5kbGVwbGE=");


    public String generateToken(RegisterRequest registerRequest) {
        Map<String, Object> claims = new HashMap<String, Object>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(registerRequest.name())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .and()
                .signWith(this.getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(this.SECRET_KEY);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }


    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return  (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExperation(token).before(new Date());
    }

    private Date extractExperation(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}