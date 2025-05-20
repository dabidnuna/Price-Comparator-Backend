package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Discount;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findByToDateAfter(LocalDate date);
    List<Discount> findByFromDateAfter(LocalDate date);
    List<Discount> findTop10ByFromDateLessThanEqualAndToDateGreaterThanEqualOrderByPercentageDesc(
            LocalDate now1, LocalDate now2
    );
    List<Discount> findByProduct_IdAndStoreNameAndFromDateLessThanEqualAndToDateGreaterThanEqual(
            String productId, String storeName, LocalDate from, LocalDate to);


}
