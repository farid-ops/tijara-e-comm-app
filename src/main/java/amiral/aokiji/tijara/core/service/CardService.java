package amiral.aokiji.tijara.core.service;

import amiral.aokiji.tijara.core.model.CardEntity;
import amiral.aokiji.tijara.swagger.CardDTO;

import java.util.Optional;

public interface CardService {
    Iterable<CardEntity> getAllCards();
    Optional<CardEntity> getCardById(String id);
    void deleteCard(String id);
    Optional<CardEntity> saveCard(CardDTO cardDTO);
}
