package amiral.aokiji.tijara.config.token;

import amiral.aokiji.tijara.utils.SecurityConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class WebToken {
    public Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getUsernameFromToken(String token){
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public Date expirationDateFromToken(String token){
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsApply){
        final Claims claims = this.getAllClaims(token);
        return claimsApply.apply(claims);
    }

    public Boolean isTokenExpired(String token){
        final Date date = this.expirationDateFromToken(token);
        return date.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(
                        new Date(System.currentTimeMillis()+ SecurityConst.TINE * 1000)).signWith(key).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username = this.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&  !isTokenExpired(token));
    }
}
