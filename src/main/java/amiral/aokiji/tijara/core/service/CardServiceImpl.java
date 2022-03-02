package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CardEntity;
import amiral.aokiji.tijara.core.model.UserEntity;
import amiral.aokiji.tijara.core.repository.CardRepository;
import amiral.aokiji.tijara.core.repository.UserRepository;
import amiral.aokiji.tijara.swagger.CardDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository){
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<CardEntity> getAllCards() {
        return this.cardRepository.findAll();
    }

    @Override
    public Optional<CardEntity> getCardById(String id) {
        return this.cardRepository.findById(UUID.fromString(id));
    }

    @Override
    public void deleteCard(String id) {
        this.cardRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Optional<CardEntity> saveCard(CardDTO cardDTO) {
        return Optional.of(this.cardRepository.save(this.toEntity(cardDTO)));
    }

    private  CardEntity toEntity(CardDTO cardDTO){
        CardEntity cardEntity = new CardEntity();

        Optional<UserEntity> user = this.userRepository.findById(UUID.fromString(cardDTO.getUserDTO().getId()));
        user.ifPresent(cardEntity::setUserEntity);

        cardEntity.setNumber(cardDTO.getNumber())
                .setExpires(cardDTO.getExpires())
                .setCvv(cardDTO.getCvv());

        return cardEntity;
    }
}
