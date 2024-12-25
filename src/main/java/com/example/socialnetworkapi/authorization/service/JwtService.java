package com.example.socialnetworkapi.authorization.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // Gizli anahtar (Bu, daha güvenli olması için dışa aktarılmamalıdır)
    private static final String SECRET_KEY = "your-secret-key";

    // Token geçerlilik süresi (Örnek: 1 saat)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 saat

    // Token oluşturma
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())  // Kullanıcı adını "subject" olarak ayarla
                .setIssuedAt(new Date())  // Token oluşturulma zamanını ayarla
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Token'ın son geçerlilik tarihini ayarla
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // Şifreleme algoritmasını ve anahtarını belirle
                .compact();
    }

    // Token'dan kullanıcı adını alma
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Token geçerlilik süresi kontrolü
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Token geçerlilik süresi
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Token doğrulama
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Claim (Özellik) çekme işlemi
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Token'dan Claims (veri) çekme
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

