package Modules.Clinical_Reporter

import geb.Module

/**
 * Created by E002183 on 4/28/2016.
 */
class ReviewReportModule extends Module {

    static content = {

        /*Header Buttons*/
        reviewReportLegend          { $("legend",text:contains("Review Report"))}
        interpretVariants           { $(".review-report-buttons div a.btn.interpret-variants").getAt(0)}
        saveFieldsButton            { $(".pull-right.buttons.review-report-buttons div .btn-success.save") }
        previewPDFButton            { $(".pull-right.buttons.review-report-buttons div .btn-info.preview") }
        setStatusButton             { $(".pull-right.buttons.review-report-buttons div span div.set-to-status-dropdown") }

        primaryReportsHeading       { $(".report-label",text:"Primary Findings")}
        secondaryReportsHeading     { $(".report-label",text:"Secondary Findings")}
        primaryFindingDiv           { $("#primary-findings")}
        secondaryFindingDiv         { $("#secondary-findings")}
        primaryFindingReports       { $("#primary-findings tr.sortable-row").size() }
        secondaryFindingReports     { $("#secondary-findings tr.sortable-row").size() }
        getClinicalReportId         { $(".clinical-report-id").text().trim() }


    }
}
