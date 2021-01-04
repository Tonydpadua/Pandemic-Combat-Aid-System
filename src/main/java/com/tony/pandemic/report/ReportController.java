package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/hospitals/reports")
public class ReportController {

    private IReportService service;

    @GetMapping
    public ResponseEntity<Report> generateReport() {

        Report report = this.service.makeReport();
        return ResponseEntity.ok().body(report);
    }

}
