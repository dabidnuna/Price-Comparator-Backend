package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ProductRecommendationDTO;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.PriceEntryRepository;
import ro.tuc.ds2020.repositories.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceEntryRepository priceEntryRepository;

    public List<ProductRecommendationDTO> getBetterAlternatives(String productId, String storeName) {
        Product reference = productRepository.findById(productId).orElse(null);
        //if (reference == null) return List.of();

        String category = reference.getCategory();
        List<Product> sameCategory = productRepository.findByCategory(category);

        LocalDate today = LocalDate.now();

        List<ProductRecommendationDTO> results = new ArrayList<>();

        for (Product product : sameCategory) {
            List<PriceEntry> prices;

            if (storeName == null || storeName.isEmpty()) {
                prices = priceEntryRepository.findByProduct_IdOrderByDateDesc(product.getId());
            } else {
                prices = priceEntryRepository.findByProduct_IdAndStoreNameOrderByDateDesc(product.getId(), storeName);
            }

            if (prices.isEmpty()) continue;

            // Cel mai recent pret
            PriceEntry latest = prices.get(0);
            double unitPrice = latest.getPrice() / product.getPackageQuantity();

            ProductRecommendationDTO dto = new ProductRecommendationDTO(
                    product.getId(),
                    product.getName(),
                    product.getBrand(),
                    round(unitPrice),
                    product.getPackageQuantity(),
                    product.getPackageUnit(),
                    latest.getStoreName()
            );

            results.add(dto);
        }

        // sortam dupa pret per unitate
        return results.stream()
                .sorted(Comparator.comparing(ProductRecommendationDTO::getUnitPrice))
                .collect(Collectors.toList());
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
