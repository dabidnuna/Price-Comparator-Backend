package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.entities.Discount;
import ro.tuc.ds2020.services.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("/best")
    public ResponseEntity<List<Discount>> getBestDiscounts() {
        List<Discount> discounts = discountService.getTopDiscounts();
        return ResponseEntity.ok(discounts);
    }
    @GetMapping("/new") //discounturi aparute azi
    public ResponseEntity<List<Discount>> getNewDiscounts() {
        List<Discount> discounts = discountService.getNewDiscounts();
        return ResponseEntity.ok(discounts);
    }
}
