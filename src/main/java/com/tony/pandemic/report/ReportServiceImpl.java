package com.tony.pandemic.report;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements IReportService {

    private IListItems listItems;
    private IPercentageHospital percentualHospital;
    private ITimeOccupatedHospital timeOccupatedHospital;
    private INegotiationHistory negotiationHistory;

    @Override
    public Report makeReport() {
        final Report report = new Report();
        report.setMediaHospitalItems(this.listItems.listItens());
        report.setPercentageHighOccupation(this.percentualHospital.percentageHighOccupation());
        report.setPercentageLowOccupation(this.percentualHospital.percentageLowOccupation());
        report.setHighOccupationMostTime(this.timeOccupatedHospital.moreOccupatedMostTime());
        report.setLowOccupationMostTime(this.timeOccupatedHospital.lessOccupatedMostTime());
        report.setNegotiationHistory(this.negotiationHistory.makeNegotiationHistory());
        return report;
    }
}
