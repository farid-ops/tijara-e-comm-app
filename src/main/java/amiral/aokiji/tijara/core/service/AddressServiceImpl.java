package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.AddressEntity;
import amiral.aokiji.tijara.core.repository.AddressRepository;
import amiral.aokiji.tijara.swagger.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Iterable<AddressEntity> getAllAddress() {
        return this.addressRepository.findAll();
    }

    @Override
    public Optional<AddressEntity> getAddressById(String id) {
        return this.addressRepository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<AddressEntity> createAddress(AddressDTO addressDTO) {
        return Optional.of(this.addressRepository.save(this.toEntity(addressDTO)));
    }

    @Override
    public void deleteAddress(String id) {
        this.addressRepository.deleteById(UUID.fromString(id));
    }

    private AddressEntity toEntity(AddressDTO addressDTO){
        AddressEntity address = new AddressEntity();
        address.setNumber(addressDTO.getNumber())
                .setResidency(addressDTO.getResidency())
                .setStreet(addressDTO.getStreet())
                .setCity(addressDTO.getCity())
                .setState(addressDTO.getState())
                .setCountry(addressDTO.getCountry())
                .setPincode(addressDTO.getPincode());
        return address;
    }
}