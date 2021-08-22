package com.tony.pandemic.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hospitals/reports")
public class ReportController {

    private final IReportService reportService;

    @GetMapping
    public ResponseEntity<Report> generateReport() {
        return ResponseEntity.ok().body(this.reportService.makeReport());
    }

}
