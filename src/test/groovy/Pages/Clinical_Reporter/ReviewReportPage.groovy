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
        Thread.sleep(2000L)
        waitFor {reviewReport.primaryFindingDiv}
            if(reviewReport.primaryFindingDiv.find(text:contains("Primary Findings")).displayed){
                return reviewReport.primaryFindingReports
            }
        return reviewReport.primaryFindingReports
    }

    def getNumberOfSecondaryFindingReports() {
        waitFor {reviewReport.secondaryFindingDiv }
            if(reviewReport.secondaryFindingDiv.find(text:contains("Secondary Findings")).displayed){
                return reviewReport.secondaryFindingReports
            }
        return reviewReport.secondaryFindingReports
    }

    def getClinicalReportId() {
        return reviewReport.getClinicalReportId
    }

    def getResponseCodeForPreviewPDF() {
        waitFor {reviewReport.previewPDFButton}
        String url = System.getProperty("geb.build.baseUrl")+"clinical_report/" + getClinicalReportId() + "/preview";
        return getResponseCode(url);
    }


}
