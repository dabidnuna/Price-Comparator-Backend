package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Discount;
import ro.tuc.ds2020.repositories.DiscountRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getTopDiscounts() {
        LocalDate today = LocalDate.now();
        return discountRepository
                .findTop10ByFromDateLessThanEqualAndToDateGreaterThanEqualOrderByPercentageDesc(today, today);
    }
    public List<Discount> getNewDiscounts() {
        LocalDate cutoff = LocalDate.now().minusDays(1);
        return discountRepository.findByFromDateAfter(cutoff);
    }
}