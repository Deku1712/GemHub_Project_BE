package exeGemHub.gemhub.jwt;

import org.slf4j.LoggerFactory;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import exeGemHub.gemhub.Entity.UserPrinciple;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service

public class JwtService {

    private static final String SECRET_KEY = "11111111111111111111111111111111";

    private static final long EXPIRE_TIME = 86400000000L;

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class.getName());



    public String generateTokenLogin(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();



        return Jwts.builder()

                .setSubject((userPrincipal.getUsername()))

                .setIssuedAt(new Date())

                 .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))

                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)

                .compact();

    }



    public boolean validateJwtToken(String authToken) {

        try {

            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);

            return true;

        } catch (SignatureException e) {

            logger.error("Invalid JWT signature -> Message: {} ", e);

        } catch (MalformedJwtException e) {

            logger.error("Invalid JWT token -> Message: {}", e);

        } catch (ExpiredJwtException e) {

            logger.error("Expired JWT token -> Message: {}", e);

        } catch (UnsupportedJwtException e) {

            logger.error("Unsupported JWT token -> Message: {}", e);

        } catch (IllegalArgumentException e) {

            logger.error("JWT claims string is empty -> Message: {}", e);

        }



        return false;

    }



    public String getUserNameFromJwtToken(String token) {



        String userName = Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(token)

                .getBody().getSubject();

        return userName;

    }

}
