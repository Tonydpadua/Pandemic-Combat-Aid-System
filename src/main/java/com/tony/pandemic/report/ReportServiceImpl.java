package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements IReportService {

    private IListItems listItems;
    private IPercentualHospital percentualHospital;
    private ITimeOccupatedHospital timeOccupatedHospital;

    @Override
    public Report makeReport() {
        final Report report = new Report();
        report.setMediaHospitalItems(this.listItems.listItens());
        report.setPercentageHighOccupation(this.percentualHospital.percentualHighOccupation());
        report.setPercentageLowOccupation(this.percentualHospital.percentualLowOccupation());
        report.setHighOccupationMostTime(this.timeOccupatedHospital.moreOccupatedMostTime());
        report.setLowOccupationMostTime(this.timeOccupatedHospital.lessOccupatedMostTime());
        return report;
    }
}
