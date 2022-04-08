package amiral.aokiji.tijara.config.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class WebTokenImpl implements WebToken {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    public String getUsernameFromToken(String token) {
        return null;
    }

    @Override
    public Date getExpirationDateFromToke(String token) {
        return null;
    }

    @Override
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        return null;
    }

    @Override
    public Claims getAllClaimsFromToken(String token) {
        return null;
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return null;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        return null;
    }
}
