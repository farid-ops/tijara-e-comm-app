package amiral.aokiji.tijara.api;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface CartApi {
    ResponseEntity<List<ItemEntity>> addCartItemsByCustomerId(String id, @Valid ItemEntity itemEntity);
    ResponseEntity<List<CartEntity>> getCartByCustomerId(String id);
}
