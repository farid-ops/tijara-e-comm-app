package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.UserEntity;
import amiral.aokiji.tijara.core.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findByUsername(username);

        if (null == user)
            throw new UsernameNotFoundException("User with username "+username+" not found");

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().stream().map(role -> authorities.add(new SimpleGrantedAuthority(role.getRolename()))).collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
