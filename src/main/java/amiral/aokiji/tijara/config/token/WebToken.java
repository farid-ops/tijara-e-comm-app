package amiral.aokiji.tijara.config.token;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface WebToken {

    String getUsernameFromToken(String token);

    Date getExpirationDateFromToke(String token);

    <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    Boolean isTokenExpired(String token);

    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails userDetails);
}
