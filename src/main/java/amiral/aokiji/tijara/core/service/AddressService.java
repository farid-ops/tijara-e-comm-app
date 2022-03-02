package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.AddressEntity;
import amiral.aokiji.tijara.swagger.AddressDTO;

import java.util.Optional;

public interface AddressService {

    Iterable<AddressEntity> getAllAddress();

    Optional<AddressEntity> getAddressById(String id);

    Optional<AddressEntity> createAddress(AddressDTO addressDTO);

    void deleteAddress(String id);
}
