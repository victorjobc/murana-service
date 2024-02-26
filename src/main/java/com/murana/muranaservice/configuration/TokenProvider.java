package com.murana.muranaservice.configuration;

import com.murana.muranaservice.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
@Component
public class TokenProvider {

    private String secretKey = "ZnNkZiBzZGZzIGdlcndnIGVyaHdlciBod2VyaGV3cmh3ZXIgaHdlcmggd2VyaCBld3Jod2VyaCB3ZXJoIGV3aCBld3Jo";
    private long validityInMilliseconds = 3600000;

    public String createToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}