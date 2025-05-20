package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PriceAlertRequestDTO;
import ro.tuc.ds2020.dtos.PriceAlertTriggerDTO;
import ro.tuc.ds2020.services.AlertService;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody PriceAlertRequestDTO dto) {
        alertService.createAlert(dto);
        return ResponseEntity.ok("Alerta creata.");
    }

    @GetMapping("/triggered")
    public ResponseEntity<List<PriceAlertTriggerDTO>> checkAlerts() {
        return ResponseEntity.ok(alertService.checkTriggeredAlerts());
    }
}
