package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dto.ProductPriceCsvRow;
import ro.tuc.ds2020.dtos.PriceAlertTriggerDTO;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.PriceEntryRepository;
import ro.tuc.ds2020.repositories.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class DataLoaderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceEntryRepository priceEntryRepository;

    @Autowired
    private AlertService alertService;

    public void importCsvData(List<ProductPriceCsvRow> csvRows, String storeName, LocalDate date) {
        for (ProductPriceCsvRow row : csvRows) {
            // verificam daca produsul exista
            Product product = productRepository.findById(row.getProductId()).orElseGet(() -> {
                Product p = new Product();
                p.setId(row.getProductId());
                p.setName(row.getProductName());
                p.setCategory(row.getProductCategory());
                p.setBrand(row.getBrand());
                p.setPackageQuantity(row.getPackageQuantity());
                p.setPackageUnit(row.getPackageUnit());
                return productRepository.save(p);
            });

            // adăugăm prețul
            PriceEntry priceEntry = new PriceEntry();
            priceEntry.setStoreName(storeName);
            priceEntry.setDate(date);
            priceEntry.setPrice(row.getPrice());
            priceEntry.setCurrency(row.getCurrency());
            priceEntry.setProduct(product);

            priceEntryRepository.save(priceEntry);

// verificam daca a fost declansata vreo alerta pentru acest produs
            List<PriceAlertTriggerDTO> alerts = alertService.checkTriggeredAlertsForProduct(product.getId());
            for (PriceAlertTriggerDTO alert : alerts) {
                System.out.println("ALERTA: " + alert.getProductName() + " este acum " + alert.getCurrentPrice() +
                        " RON in " + alert.getStoreName() + " (sub pragul de " + alert.getTargetPrice() + " RON pentru " + alert.getUserEmail() + ")");
            }

        }
    }
}
