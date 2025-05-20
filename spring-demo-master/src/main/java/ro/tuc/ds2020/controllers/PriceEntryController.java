package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.PriceAlertTriggerDTO;
import ro.tuc.ds2020.dtos.PriceEntryRequestDTO;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.PriceEntryRepository;
import ro.tuc.ds2020.repositories.ProductRepository;
import ro.tuc.ds2020.services.AlertService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PriceEntryController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceEntryRepository priceEntryRepository;
    @Autowired
    private AlertService alertService;

    @PostMapping("/add")
    public ResponseEntity<?> addPrice(@RequestBody PriceEntryRequestDTO dto) {
        Product product = productRepository.findById(dto.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body("Produsul nu exista");
        }

        PriceEntry entry = new PriceEntry();
        entry.setProduct(product);
        entry.setStoreName(dto.getStoreName());
        entry.setPrice(dto.getPrice());
        entry.setCurrency(dto.getCurrency());
        entry.setDate(LocalDate.now());

        priceEntryRepository.save(entry);

        // verificam alertele pentru produs de fiecare data cand se adauga un pret nou pentru un produs
        List<PriceAlertTriggerDTO> alerts = alertService.checkTriggeredAlertsForProduct(dto.getProductId());

        return ResponseEntity.ok(alerts.isEmpty()
                ? "Pret adaugat. Nicio alerta activata."
                : alerts);
    }
}
