package com.sumrid_k.pos.Report.controller;

import com.sumrid_k.pos.Report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public ResponseEntity getReport() {
        return ResponseEntity.ok(reportService.getReports());
    }

    // for get data from all service
    // FOR TEST
    @GetMapping("/get-all-data")
    public void getData() {
        reportService.getDataAllServices();
    }
}
