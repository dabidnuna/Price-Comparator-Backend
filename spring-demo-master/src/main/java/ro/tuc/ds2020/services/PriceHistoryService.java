package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.PricePointWithStoreDTO;
import ro.tuc.ds2020.entities.PriceEntry;
import ro.tuc.ds2020.repositories.PriceEntryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceEntryRepository priceEntryRepository;

    public List<PriceEntry> getHistory(String productId, String storeName) {
        if (storeName == null || storeName.isEmpty()) {
            return priceEntryRepository.findByProduct_IdOrderByDateAsc(productId);
        } else {
            return priceEntryRepository.findByProduct_IdAndStoreNameOrderByDateAsc(productId, storeName);
        }
    }
    public List<PricePointWithStoreDTO> getHistoryWithStores(String productId, String storeName) {
        List<PriceEntry> entries;

        if (storeName == null || storeName.isEmpty()) {
            entries = priceEntryRepository.findByProduct_IdOrderByDateAsc(productId);
        } else {
            entries = priceEntryRepository.findByProduct_IdAndStoreNameOrderByDateAsc(productId, storeName);
        }

        return entries.stream()
                .map(e -> new PricePointWithStoreDTO(e.getDate(), e.getPrice(), e.getStoreName()))
                .collect(Collectors.toList());
    }

}
