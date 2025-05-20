package ro.tuc.ds2020.services;

import org.springframework.stereotype.Service;
import ro.tuc.ds2020.dto.ProductPriceCsvRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImporterService {

    public List<ProductPriceCsvRow> readPriceCsv(Path pathToFile) throws IOException {
        List<ProductPriceCsvRow> entries = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length < 8) continue;

                ProductPriceCsvRow row = new ProductPriceCsvRow();
                row.setProductId(tokens[0]);
                row.setProductName(tokens[1]);
                row.setProductCategory(tokens[2]);
                row.setBrand(tokens[3]);
                row.setPackageQuantity(Double.parseDouble(tokens[4]));
                row.setPackageUnit(tokens[5]);
                row.setPrice(Double.parseDouble(tokens[6]));
                row.setCurrency(tokens[7]);

                entries.add(row);
            }
        }

        return entries;
    }
}
