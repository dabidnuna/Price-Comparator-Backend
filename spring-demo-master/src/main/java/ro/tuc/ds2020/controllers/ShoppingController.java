package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.ShoppingBasketResponse;
import ro.tuc.ds2020.dtos.ShoppingItemRequest;
import ro.tuc.ds2020.services.ShoppingOptimizerService;

import java.util.List;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingOptimizerService optimizerService;

    @PostMapping("/optimize")
    public ResponseEntity<ShoppingBasketResponse> optimizeBasket(@RequestBody List<ShoppingItemRequest> request) {
        return ResponseEntity.ok(optimizerService.optimize(request));
    }
}
