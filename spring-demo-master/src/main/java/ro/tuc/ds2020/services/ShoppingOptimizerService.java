package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.ShoppingBasketResponse;
import ro.tuc.ds2020.dtos.ShoppingItemRequest;
import ro.tuc.ds2020.dtos.ShoppingItemResponse;
import ro.tuc.ds2020.entities.Discount;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.DiscountRepository;
import ro.tuc.ds2020.repositories.PriceEntryRepository;
import ro.tuc.ds2020.repositories.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingOptimizerService {

    @Autowired
    private PriceEntryRepository priceEntryRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private ProductRepository productRepository;

    public ShoppingBasketResponse optimize(List<ShoppingItemRequest> requests) {
        List<ShoppingItemResponse> resultItems = new ArrayList<>();
        double totalCost = 0.0;
        LocalDate today = LocalDate.now();

        for (ShoppingItemRequest req : requests) {
            String productId = req.getProductId();
            int quantity = req.getQuantity();

            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) continue;

            // Toate preturile cele mai recente per magazin
            List<PriceEntry> allEntries = priceEntryRepository.findByProduct_Id(productId);

            Map<String, PriceEntry> latestPerStore = new HashMap<>();
            for (PriceEntry entry : allEntries) {
                String store = entry.getStoreName();
                if (!latestPerStore.containsKey(store) || entry.getDate().isAfter(latestPerStore.get(store).getDate())) {
                    latestPerStore.put(store, entry);
                }
            }

            double minTotal = Double.MAX_VALUE;
            ShoppingItemResponse bestItem = null;

            for (Map.Entry<String, PriceEntry> e : latestPerStore.entrySet()) {
                PriceEntry priceEntry = e.getValue();
                double unitPrice = priceEntry.getPrice();

                // Verifica daca exista o reducere activa
                List<Discount> activeDiscounts = discountRepository
                        .findByProduct_IdAndStoreNameAndFromDateLessThanEqualAndToDateGreaterThanEqual(
                                productId, priceEntry.getStoreName(), today, today);

                if (!activeDiscounts.isEmpty()) {
                    int percentage = activeDiscounts.get(0).getPercentage();
                    unitPrice = unitPrice * (100 - percentage) / 100.0;
                }

                double totalPrice = unitPrice * quantity;

                if (totalPrice < minTotal) {
                    minTotal = totalPrice;

                    ShoppingItemResponse item = new ShoppingItemResponse();
                    item.setProductId(productId);
                    item.setProductName(product.getName());
                    item.setQuantity(quantity);
                    item.setStore(priceEntry.getStoreName());
                    item.setUnitPrice(round(unitPrice));
                    item.setTotalPrice(round(totalPrice));

                    bestItem = item;
                }
            }

            if (bestItem != null) {
                resultItems.add(bestItem);
                totalCost += bestItem.getTotalPrice();
            }
        }

        ShoppingBasketResponse response = new ShoppingBasketResponse();
        response.setTotalOptimizedCost(round(totalCost));
        response.setItems(resultItems);
        return response;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
