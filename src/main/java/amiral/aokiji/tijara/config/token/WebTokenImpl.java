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
public class WebTokenImpl implements WebToken {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String getUsernameFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    @Override
    public Date getExpirationDateFromToke(String token) {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    @Override
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToke(token);
        return expiration.before(new Date());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * SecurityConst.TINE * 1000))
                .signWith(key).compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = this.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) & !isTokenExpired(token));
    }
}
