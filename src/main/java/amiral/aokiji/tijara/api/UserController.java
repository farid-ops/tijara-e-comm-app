package amiral.aokiji.tijara.api;

import amiral.aokiji.tijara.config.token.WebToken;
import amiral.aokiji.tijara.core.model.UserEntity;
import amiral.aokiji.tijara.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "/api/v1")
public class UserController {
    private final WebToken webToken;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(WebToken webToken,
                          AuthenticationManager authenticationManager,
                          UserService userService) {
        this.webToken = webToken;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticaationToken(@RequestBody UserEntity userEntity) throws Exception{
        Map<String, Object> response = new HashMap<>();
        try{
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
            UserDetails userDetails = this.userService.loadUserByUsername(userEntity.getUsername());
            String token = this.webToken.generateToken(userDetails);
            if (authentication.isAuthenticated()){
                response.put("Error", false);
                response.put("LongIn", true);
                response.put("Token", token);
                return ResponseEntity.ok(response);
            }else{
                response.put("Error", true);
                response.put("Invalid credential", true);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }catch (DisabledException e) {
            e.printStackTrace();
            response.put("error", true);
            response.put("message", "User is disabled");
            return ResponseEntity.status(500).body(response);
        } catch (BadCredentialsException e) {
            response.put("error", true);
            response.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", true);
            response.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(response);
        }
        
    }
}
