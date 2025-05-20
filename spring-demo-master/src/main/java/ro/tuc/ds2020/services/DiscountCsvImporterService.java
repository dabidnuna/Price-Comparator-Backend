package ro.tuc.ds2020.services;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dtos.DiscountCsvRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountCsvImporterService {
    public List<DiscountCsvRow> readDiscountCsv(Path pathToFile) throws IOException {
        List<DiscountCsvRow> entries = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                DiscountCsvRow row = new DiscountCsvRow();
                row.setProductId(tokens[0]);
                row.setProductName(tokens[1]);
                row.setBrand(tokens[2]);
                row.setPackageQuantity(Double.parseDouble(tokens[3]));
                row.setPackageUnit(tokens[4]);
                row.setProductCategory(tokens[5]);
                row.setFromDate(LocalDate.parse(tokens[6]));
                row.setToDate(LocalDate.parse(tokens[7]));
                row.setPercentage(Integer.parseInt(tokens[8]));
                entries.add(row);
            }
        }

        return entries;
    }
}
