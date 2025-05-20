package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.PricePointWithStoreDTO;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.services.PriceHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceHistoryService priceHistoryService;

    @GetMapping("/history")
    public ResponseEntity<List<PriceEntry>> getPriceHistory(
            String productId,
             String storeName
    ) {
        List<PriceEntry> history = priceHistoryService.getHistory(productId, storeName);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/history/graph/stores")
    public ResponseEntity<List<PricePointWithStoreDTO>> getPriceGraphPerStore(
            @RequestParam String productId,
            @RequestParam(required = false) String storeName
    ) {
        return ResponseEntity.ok(
                priceHistoryService.getHistoryWithStores(productId, storeName)
        );
    }
}