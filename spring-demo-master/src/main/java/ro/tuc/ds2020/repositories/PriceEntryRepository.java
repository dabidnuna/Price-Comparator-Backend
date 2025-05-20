package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.PriceEntry;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceEntryRepository extends JpaRepository<PriceEntry, Long> {

    // Preturi pentru un produs intr-un anumit magazin
    List<PriceEntry> findByProduct_IdAndStoreName(String productId, String storeName);

    // Preturi pentru un produs in toate magazinele
    List<PriceEntry> findByProduct_Id(String productId);

    // Preturi pentru un magazin la o anumita data
    List<PriceEntry> findByStoreNameAndDate(String storeName, LocalDate date);

    // Preturi intre anumite date (pentru istoric, trenduri)
    List<PriceEntry> findByProduct_IdAndDateBetween(String productId, LocalDate start, LocalDate end);

    // Cel mai recent pret pentru un produs intr-un magazin
    PriceEntry findTopByProduct_IdAndStoreNameOrderByDateDesc(String productId, String storeName);

    List<PriceEntry> findByProduct_IdOrderByDateAsc(String productId);
    List<PriceEntry> findByProduct_IdOrderByDateDesc(String productId);

    List<PriceEntry> findByProduct_IdAndStoreNameOrderByDateAsc(String productId, String storeName);


    List<PriceEntry> findByProduct_IdAndStoreNameOrderByDateDesc(String productId, String storeName);

}
