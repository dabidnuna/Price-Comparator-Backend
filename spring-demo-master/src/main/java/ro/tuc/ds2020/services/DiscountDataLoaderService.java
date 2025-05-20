package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DiscountCsvRow;
import ro.tuc.ds2020.entities.Discount;
import ro.tuc.ds2020.entities.Product;
import ro.tuc.ds2020.repositories.DiscountRepository;
import ro.tuc.ds2020.repositories.ProductRepository;

import java.util.List;

@Service
public class DiscountDataLoaderService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    public void importDiscountData(List<DiscountCsvRow> rows, String storeName) {
        for (DiscountCsvRow row : rows) {
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

            Discount discount = new Discount();
            discount.setStoreName(storeName);
            discount.setFromDate(row.getFromDate());
            discount.setToDate(row.getToDate());
            discount.setPercentage(row.getPercentage());
            discount.setProduct(product);

            discountRepository.save(discount);
        }
    }
}