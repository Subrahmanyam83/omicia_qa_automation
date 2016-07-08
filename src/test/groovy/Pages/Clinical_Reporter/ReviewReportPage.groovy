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
        reviewReport.reviewReportLegend
    }

    static content = {
        reviewReport { module ReviewReportModule }
    }

    def getNumberOfPrimaryFindingReports() {
        Thread.sleep(2000L)
        waitFor {reviewReport.primaryFindingDiv}
        3.times {
            try{
                if(reviewReport.primaryFindingDiv.find(text:contains("Primary Findings")).displayed){
                    return reviewReport.primaryFindingReports
                }
            }
            catch (StaleElementReferenceException){
                Thread.sleep(500)
            }
        }
        return reviewReport.primaryFindingReports
    }

    def getNumberOfSecondaryFindingReports() {
        waitFor {reviewReport.secondaryFindingDiv }
        3.times {
            try {
                if(reviewReport.secondaryFindingDiv.find(text: contains("Secondary Findings")).displayed) {
                    return reviewReport.secondaryFindingReports
                }
            }
            catch (StaleElementReferenceException) {
                Thread.sleep(500)
            }
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
