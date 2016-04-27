package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ClinicalReporterModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/26/2016.
 */
class ClinicalReporterPage extends BasePage {

    static at = {
        clinicalReporter.newReport.displayed
    }

    static content = {
        clinicalReporter { module ClinicalReporterModule }
    }

    def clickOnNewReportAndSelectDropDownValue(String option) {
        click(clinicalReporter.newReport, "New Clinical Report Button")
        click(clinicalReporter.newReportDropDownValue(option), "New Clinical Report Drop Down Value: " + option)
    }

    def createNewPanelReport(String patientId, String panel, String filter, String project, String assayType = "", boolean includeCosmicEvidence = false) {
        type(clinicalReporter.patientIdTextField, patientId, "Patient ID Text Field")

        click(clinicalReporter.choosePanelDropDown, "Choose Panel Drop Down")
        click(clinicalReporter.chooseDropDownValueBasedOntheValue(panel), "Choose Panel Drop Down Value: " + panel)

        click(clinicalReporter.chooseFilterDropDown, "Choose Filter Drop Down")
        click(clinicalReporter.chooseDropDownValueBasedOntheValue(filter), "Choose Filter Drop Down Value: " + filter)

        click(clinicalReporter.chooseProjectDropDown, "Choose Project Drop Down")
        click(clinicalReporter.chooseDropDownValueBasedOntheValue(project), "Choose Project Drop Down Value: " + project);

        if (assayType != "") {
            click(clinicalReporter.chooseAssayTypeDropDown, "Choose Assay Type Drop Down")
            click(clinicalReporter.chooseDropDownValueBasedOntheValue(assayType), "Choose Assay Type Drop Down Value: " + assayType)
        }

        if (includeCosmicEvidence.equals(true)) {
            click(clinicalReporter.includeCosmicCheckBox, "Include Cosmic checkbox");
        }
    }

    def selectGenomeFromGenomeList(String genomeName) {
        waitFor { clinicalReporter.genomeFromGenomeList(genomeName).displayed }
        click(clinicalReporter.genomeFromGenomeList(genomeName), "Genome: '" + genomeName + "' from the Genome List")
    }

    def selectSaveNewPanelReport() {
        click(clinicalReporter.saveButton, "Clinical Report Save Button")
        waitFor { clinicalReporter.reportTable.displayed }
    }

    def refreshTillStatusChangesToReadyForInterpretation(String patientId) {
        while (!clinicalReporter.getStatusBasedonPatientId(patientId).equals("Ready for Interpretation")) {
            driver.get(driver.currentUrl)
            waitFor { clinicalReporter.reportTable.displayed }
        }
    }

    def clickOnActionsAndValueBasedOnPatientId(String patientId, String action) {
        click(clinicalReporter.actionsButtonBasedOnPatientId(patientId), "Action Button of the Clinical Report of Patient ID: " + patientId)
        click(clinicalReporter.valueOnActionButon(action), "Action button Value: " + action)
    }

    def deleteAllClinicalReports() {
        while (!clinicalReporter.numberOfClinicalReports.equals(ZERO)) {
            click(clinicalReporter.actionsButton, "Actions Button on Clinical Report")
            click(clinicalReporter.deleteReportOption, "Delete Report Option")
            click(clinicalReporter.deleteReportButtonOnDialog, "Confirmation Delete Report Button on Dialog")
            waitTillElementIsNotPresent(clinicalReporter.deletingReportProgressBar, "Deleting Report Progress Bar")
            Thread.sleep(500)
        }
    }
}
