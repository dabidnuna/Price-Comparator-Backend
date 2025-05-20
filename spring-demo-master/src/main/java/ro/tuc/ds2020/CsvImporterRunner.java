package ro.tuc.ds2020;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.DiscountCsvRow;
import ro.tuc.ds2020.services.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Component
public class CsvImporterRunner implements CommandLineRunner {

    @Autowired
    private CsvImporterService productCsvService;
    @Autowired
    private DiscountCsvImporterService discountCsvService;
    @Autowired
    private DataLoaderService dataLoaderService;
    @Autowired
    private DiscountDataLoaderService discountLoaderService;

    @Override
    public void run(String... args) throws Exception {

        // ==== Lidl - 2025-05-01 ====
        Path lidlPrices1 = Paths.get("src/main/resources/lidl_2025-05-01.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> lidlRows1 = productCsvService.readPriceCsv(lidlPrices1);
        dataLoaderService.importCsvData(lidlRows1, "Lidl", LocalDate.of(2025, 5, 1));

        Path lidlDiscounts1 = Paths.get("src/main/resources/lidl_discounts_2025-05-01.csv");
        List<DiscountCsvRow> lidlDiscRows1 = discountCsvService.readDiscountCsv(lidlDiscounts1);
        discountLoaderService.importDiscountData(lidlDiscRows1, "Lidl");

        // ==== Lidl - 2025-05-08 ====
        Path lidlPrices2 = Paths.get("src/main/resources/lidl_2025-05-08.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> lidlRows2 = productCsvService.readPriceCsv(lidlPrices2);
        dataLoaderService.importCsvData(lidlRows2, "Lidl", LocalDate.of(2025, 5, 8));

        Path lidlDiscounts2 = Paths.get("src/main/resources/lidl_discounts_2025-05-08.csv");
        List<DiscountCsvRow> lidlDiscRows2 = discountCsvService.readDiscountCsv(lidlDiscounts2);
        discountLoaderService.importDiscountData(lidlDiscRows2, "Lidl");

        // ==== Profi - 2025-05-01 ====
        Path profiPrices1 = Paths.get("src/main/resources/profi_2025-05-01.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> profiRows1 = productCsvService.readPriceCsv(profiPrices1);
        dataLoaderService.importCsvData(profiRows1, "Profi", LocalDate.of(2025, 5, 1));

        Path profiDiscounts1 = Paths.get("src/main/resources/profi_discounts_2025-05-01.csv");
        List<DiscountCsvRow> profiDiscRows1 = discountCsvService.readDiscountCsv(profiDiscounts1);
        discountLoaderService.importDiscountData(profiDiscRows1, "Profi");

        // ==== Profi - 2025-05-08 ====
        Path profiPrices2 = Paths.get("src/main/resources/profi_2025-05-08.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> profiRows2 = productCsvService.readPriceCsv(profiPrices2);
        dataLoaderService.importCsvData(profiRows2, "Profi", LocalDate.of(2025, 5, 8));

        Path profiDiscounts2 = Paths.get("src/main/resources/profi_discounts_2025-05-08.csv");
        List<DiscountCsvRow> profiDiscRows2 = discountCsvService.readDiscountCsv(profiDiscounts2);
        discountLoaderService.importDiscountData(profiDiscRows2, "Profi");

        // ==== Kaufland - 2025-05-01 ====
        Path kauflandPrices1 = Paths.get("src/main/resources/kaufland_2025-05-01.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> kauflandRows1 = productCsvService.readPriceCsv(kauflandPrices1);
        dataLoaderService.importCsvData(kauflandRows1, "Kaufland", LocalDate.of(2025, 5, 1));

        Path kauflandDiscounts1 = Paths.get("src/main/resources/kaufland_discounts_2025-05-01.csv");
        List<DiscountCsvRow> kauflandDiscRows1 = discountCsvService.readDiscountCsv(kauflandDiscounts1);
        discountLoaderService.importDiscountData(kauflandDiscRows1, "Kaufland");

        // ==== Kaufland - 2025-05-08 ====
        Path kauflandPrices2 = Paths.get("src/main/resources/kaufland_2025-05-08.csv");
        List<ro.tuc.ds2020.dto.ProductPriceCsvRow> kauflandRows2 = productCsvService.readPriceCsv(kauflandPrices2);
        dataLoaderService.importCsvData(kauflandRows2, "Kaufland", LocalDate.of(2025, 5, 8));

        Path kauflandDiscounts2 = Paths.get("src/main/resources/kaufland_discounts_2025-05-08.csv");
        List<DiscountCsvRow> kauflandDiscRows2 = discountCsvService.readDiscountCsv(kauflandDiscounts2);
        discountLoaderService.importDiscountData(kauflandDiscRows2, "Kaufland");

        System.out.println("CSV-urile au fost importate cu succes în baza de date!");
    }
}
