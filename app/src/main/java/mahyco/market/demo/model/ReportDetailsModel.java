package mahyco.market.demo.model;

import java.util.List;

public class ReportDetailsModel {
    public List<ReportMasterModel> getDemoSegmentDetailsModel() {
        return demoSegmentDetailsModel;
    }

    public void setDemoSegmentDetailsModel(List<ReportMasterModel> demoSegmentDetailsModel) {
        this.demoSegmentDetailsModel = demoSegmentDetailsModel;
    }

    public List<CharatristicsMenuReportDetailsModel> getDemoSowingCharacteristicsMenuModel() {
        return demoSowingCharacteristicsMenuModel;
    }

    public void setDemoSowingCharacteristicsMenuModel(List<CharatristicsMenuReportDetailsModel> demoSowingCharacteristicsMenuModel) {
        this.demoSowingCharacteristicsMenuModel = demoSowingCharacteristicsMenuModel;
    }

    List<ReportMasterModel> demoSegmentDetailsModel;
    List<CharatristicsMenuReportDetailsModel> demoSowingCharacteristicsMenuModel;
}
