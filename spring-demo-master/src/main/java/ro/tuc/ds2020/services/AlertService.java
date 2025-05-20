package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.PriceAlertRequestDTO;
import ro.tuc.ds2020.dtos.PriceAlertTriggerDTO;
import ro.tuc.ds2020.entities.PriceAlert;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.PriceAlertRepository;
import ro.tuc.ds2020.repositories.PriceEntryRepository;
import ro.tuc.ds2020.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private PriceAlertRepository alertRepo;
    @Autowired
    private PriceEntryRepository priceRepo;
    @Autowired
    private ProductRepository productRepo;

    public void createAlert(PriceAlertRequestDTO dto) {
        Product product = productRepo.findById(dto.getProductId()).orElse(null);
        if (product == null) return;

        PriceAlert alert = new PriceAlert();
        alert.setProduct(product);
        alert.setUserEmail(dto.getUserEmail());
        alert.setTargetPrice(dto.getTargetPrice());

        alertRepo.save(alert);
    }

    public List<PriceAlertTriggerDTO> checkTriggeredAlerts() {
        List<PriceAlert> allAlerts = alertRepo.findAll();
        List<PriceAlertTriggerDTO> triggered = new ArrayList<>();

        for (PriceAlert alert : allAlerts) {
            List<PriceEntry> recentPrices = priceRepo
                    .findByProduct_IdOrderByDateDesc(alert.getProduct().getId());

            if (!recentPrices.isEmpty()) {
                PriceEntry latest = recentPrices.get(0);
                if (latest.getPrice() <= alert.getTargetPrice()) {
                    triggered.add(new PriceAlertTriggerDTO(
                            alert.getUserEmail(),
                            alert.getProduct().getName(),
                            latest.getStoreName(),
                            latest.getPrice(),
                            alert.getTargetPrice()
                    ));
                }
            }
        }

        return triggered;
    }
    public List<PriceAlertTriggerDTO> checkTriggeredAlertsForProduct(String productId) {
        List<PriceAlert> alerts = alertRepo.findByProduct_Id(productId);
        List<PriceAlertTriggerDTO> triggered = new ArrayList<>();

        if (alerts.isEmpty()) return triggered;

        List<PriceEntry> recentPrices = priceRepo.findByProduct_IdOrderByDateDesc(productId);
        if (recentPrices.isEmpty()) return triggered;

        PriceEntry latest = recentPrices.get(0);

        for (PriceAlert alert : alerts) {
            if (latest.getPrice() <= alert.getTargetPrice()) {
                triggered.add(new PriceAlertTriggerDTO(
                        alert.getUserEmail(),
                        alert.getProduct().getName(),
                        latest.getStoreName(),
                        latest.getPrice(),
                        alert.getTargetPrice()
                ));
            }
        }

        return triggered;
    }

}