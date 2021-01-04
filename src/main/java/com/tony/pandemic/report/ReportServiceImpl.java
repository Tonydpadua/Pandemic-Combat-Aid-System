package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements  IReportService{

    private IListItems listItems;
    private IPercentualHospital percentualHospital;

    @Override
    public Report makeReport() {
        final Report report = new Report();
        report.setMediaHospitalItems(this.listItems.listItens());
        report.setPercentageHighOccupation(this.percentualHospital.percentualHighOccupation());
        report.setPercentageLowOccupation(this.percentualHospital.percentualLowOccupation());
        return report;
    }
}
