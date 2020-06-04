/*
 *  Javier Zudaire
 */
package servidortienda;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.time.Clock;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JwtTokenUtil {

    byte[] decodedKey = Base64.getDecoder().decode("WfoObVRwQOYRMDRRCWyifQGwhQv0Z8f3piRoo4Ppw1A=");
    SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

    public String generateToken(String userid) {
        Date date = new Date();
        String jws = Jwts.builder()
                .setSubject(userid)
                .signWith(key)
                .setExpiration(new Date(date.getTime() + (30 * 60000)))
                .compact();

        return jws;
    }

    public boolean validateToken(String token) {

        Jws<Claims> jws;
        Clock clock = Clock.systemDefaultZone();

        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            if (isTokenExpired(jws.getBody().getExpiration()) == false) {
                return true;
            } else {
                return false;
            }

        } catch (JwtException ex) {

            return false;
        }

    }

    private Boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getUser(String token) {

        Jws<Claims> jws;

        jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return jws.getBody().getSubject();

    }

}
