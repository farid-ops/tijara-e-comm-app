package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<UserEntity> findById(String id);

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> update(String id, UserEntity newUser);

    void delete(String id);
}
