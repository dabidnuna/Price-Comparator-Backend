package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.tuc.ds2020.dtos.ProductRecommendationDTO;
import ro.tuc.ds2020.services.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/better-alternatives")
    public ResponseEntity<List<ProductRecommendationDTO>> getAlternatives(
            @RequestParam String productId,
            @RequestParam(required = false) String storeName
    ) {
        return ResponseEntity.ok(
                recommendationService.getBetterAlternatives(productId, storeName)
        );
    }
}
