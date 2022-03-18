package amiral.aokiji.tijara.api;

import amiral.aokiji.tijara.core.model.CartEntity;
import amiral.aokiji.tijara.core.model.ItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController(value = "/api/v1/carts")
public class CartControllerApiImpl implements CartApi {

    private static final Logger logger = LoggerFactory.getLogger(CartControllerApiImpl.class);

    @PostMapping(value = "/{customerId}/item")
    @Override
    public ResponseEntity<List<ItemEntity>> addCartItemsByCustomerId(@PathVariable String customerId, @Valid ItemEntity itemEntity) {
        logger.info("Request for customer ID : {}\nItem: {}", customerId, itemEntity);
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "/{customerId}")
    @Override
    public ResponseEntity<List<CartEntity>> getCartByCustomerId(@PathVariable String customerId) {
        throw new RuntimeException("Manual exception thrown");
    }
}
