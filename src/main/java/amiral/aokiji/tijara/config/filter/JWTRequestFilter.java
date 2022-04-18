package amiral.aokiji.tijara.config.filter;

import amiral.aokiji.tijara.config.token.WebToken;
import amiral.aokiji.tijara.core.service.UserService;
import amiral.aokiji.tijara.utils.SecurityConst;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final WebToken webToken;
    private static Logger logger = LoggerFactory.getLogger(JWTRequestFilter.class);

    public JWTRequestFilter(UserService userService,
                            WebToken webToken) {
        this.userService = userService;
        this.webToken = webToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(SecurityConst.AUTHORIZATION);
        if (StringUtils.startsWith(header, SecurityConst.PREFIX)){
            String token = header.substring(7);
            try{
                String username = this.webToken.getUsernameFromToken(token);
                if (StringUtils.isNotEmpty(username) && null== SecurityContextHolder.getContext().getAuthentication()){
                    UserDetails userDetails = this.userService.loadUserByUsername(username);
                    if (this.webToken.validateToken(token, userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }catch (IllegalArgumentException e){
                logger.error("Unable to fetch Jwt.");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token is expired");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        filterChain.doFilter(request, response);
    }
}
