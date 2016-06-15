package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ReviewReportModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/28/2016.
 */
class ReviewReportPage extends BasePage {

    static at = {
        reviewReport.interpretVariants.displayed
        reviewReport.previewPDFButton.displayed
    }

    static content = {
        reviewReport { module ReviewReportModule }
    }

    def getNumberOfPrimaryFindingReports() {
        waitFor {reviewReport.primaryReportsHeading}
        return reviewReport.primaryFindingReports
    }

    def getNumberOfSecondaryFindingReports() {
        waitFor {reviewReport.secondaryReportsHeading}
        return reviewReport.secondaryFindingReports
    }

    def getClinicalReportId() {
        return reviewReport.getClinicalReportId
    }

    def getResponseCodeForPreviewPDF() {
        String url = "http://test1.omicia-private.com/clinical_report/" + getClinicalReportId() + "/preview";
        return getResponseCode(url);
    }


}
