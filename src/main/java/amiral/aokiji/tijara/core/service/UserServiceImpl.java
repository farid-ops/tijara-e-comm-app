package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.UserEntity;
import amiral.aokiji.tijara.core.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (null==userEntity)
            throw new UsernameNotFoundException("User not found for this username "+username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().stream().map(role->authorities.add(new SimpleGrantedAuthority(role.getRolename()))).collect(Collectors.toList());
        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return this.userRepository.findById(UUID.fromString(id));
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> update(String id, UserEntity newUser) {
        Optional<UserEntity> oldUser = this.findById(id);
        if (null == oldUser)
            throw new UsernameNotFoundException("User for this id "+id+" is not found.");
        oldUser.stream().map(user->{
           user.setEmail(newUser.getEmail());
           user.setFirstname(newUser.getFirstname());
           user.setLastname(newUser.getLastname());
           user.setPassword(newUser.getPassword());
           user.setUserstatus(newUser.getUserstatus());
           user.setPhone(newUser.getPhone());
           user.setRoles(newUser.getRoles());
            return user;
        }).map(this.userRepository::save);
        return null;
    }

    @Override
    public void delete(String id) {
        Optional<UserEntity> userFound = this.findById(id);
        if (null == userFound)
            throw new RuntimeException("user not found");
        else
            userFound.map(user-> {
                this.userRepository.delete(user);
                return Optional.empty();
            });
    }
}
