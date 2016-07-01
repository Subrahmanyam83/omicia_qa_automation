package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.ClinicalReporterModule
import Modules.Filtering_Protocol.NewFilteringProtocolModule
import Modules.Panel_Builder.PanelBuilderModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 4/26/2016.
 */
class ClinicalReporterPage extends BasePage {

    static at = {
        clinicalReporter.newReport.displayed
        clinicalReporter.clinicalReportsTable
        clinicalReporter.loadingReportsProgress.firstElement().getCssValue("display").equals("none")
    }

    static content = {
        clinicalReporter { module ClinicalReporterModule }
        filteringprotocol {module NewFilteringProtocolModule}
        panelBuilder {module PanelBuilderModule}
    }

    def clickOnNewReportAndSelectDropDownValue(String option) {
        click(clinicalReporter.newReport, "New Clinical Report Button")
        click(clinicalReporter.newReportDropDownValue(option), "New Clinical Report Drop Down Value: " + option)
    }

    def fillDetailsForNewReport(String patientId, String project, String panel = "", String filter = "", String assayType = "", boolean includeCosmicEvidence = false) {
        type(clinicalReporter.patientIdTextField, patientId, "Patient ID Text Field")

        if (panel != "") {
            waitFor {clinicalReporter.choosePanelDropDown}
            click(clinicalReporter.choosePanelDropDown, "Choose Panel Drop Down")
            waitFor { clinicalReporter.chooseDropDownValueBasedOntheValue(panel).displayed }
            click(clinicalReporter.chooseDropDownValueBasedOntheValue(panel), "Choose Panel Drop Down Value: " + panel)
        }

        if (filter != "") {
            waitFor {clinicalReporter.chooseFilterDropDown}
            click(clinicalReporter.chooseFilterDropDown, "Choose Filter Drop Down")
            waitFor { clinicalReporter.chooseDropDownValueBasedOntheValue(filter) }
            click(clinicalReporter.chooseDropDownValueBasedOntheValue(filter), "Choose Filter Drop Down Value: " + filter)
        }

        waitFor {clinicalReporter.chooseProjectDropDown}
        click(clinicalReporter.chooseProjectDropDown, "Choose Project Drop Down")
        waitFor { clinicalReporter.chooseDropDownValueBasedOntheValue(project) }
        click(clinicalReporter.chooseDropDownValueBasedOntheValue(project), "Choose Project Drop Down Value: " + project);

        if (assayType != "") {
            waitFor {clinicalReporter.chooseAssayTypeDropDown}
            click(clinicalReporter.chooseAssayTypeDropDown, "Choose Assay Type Drop Down")
            waitFor { clinicalReporter.chooseDropDownValueBasedOntheValue(assayType) }
            click(clinicalReporter.chooseDropDownValueBasedOntheValue(assayType), "Choose Assay Type Drop Down Value: " + assayType)
        }

        if (includeCosmicEvidence.equals(true)) {
            waitFor {clinicalReporter.includeCosmicCheckBox}
            click(clinicalReporter.includeCosmicCheckBox, "Include Cosmic checkbox");
        }
    }

    def selectSaveNewPanelReport() {
        click(clinicalReporter.saveButton, "Clinical Report Save Button")
        waitFor { clinicalReporter.reportTable.displayed }
    }

    def refreshTillStatusChangesToReadyForInterpretation(String patientId) {
        int index = 0;
        while (!clinicalReporter.getStatusBasedonPatientId(patientId).equals("Ready for Interpretation")) {
            driver.navigate().refresh()
            waitFor { clinicalReporter.reportTable.displayed }
            index++;
            Thread.sleep(1000L)
            if (clinicalReporter.getStatusBasedonPatientId(patientId).equals("Failed")) {
                Assert.fail("Clinical Report Failed for the Report ID: " + patientId)
            }
            if (index.equals(THIRTY)) {
                Assert.fail("Pipeline is busy or Down: 'Status of the Clinical Report is not changing to :'Ready for Interpretation'")
            }
        }
    }

    def clickOnActionsAndValueBasedOnPatientId(String patientId, String action) {
        waitFor {clinicalReporter.actionsButtonBasedOnPatientId(patientId)}
        click(clinicalReporter.actionsButtonBasedOnPatientId(patientId), "Action Button of the Clinical Report of Patient ID: " + patientId)
        waitFor {clinicalReporter.valueOnActionButon(action)}
        click(clinicalReporter.valueOnActionButon(action), "Action button Value: " + action)
    }

    def getNamesOfMembers() {
        List members = new LinkedList()
        for (int i = 0; i < clinicalReporter.numberOfMembersInNewVAASTAnalysis.size(); i++) {
            members.add(clinicalReporter.numberOfMembersInNewVAASTAnalysis[i].text())
        }
        return members.sort()
    }

    def deleteAllClinicalReports() {
        while (!clinicalReporter.numberOfClinicalReports.equals(ZERO)) {
            click(clinicalReporter.actionsButton, "Actions Button on Clinical Report")
            click(clinicalReporter.deleteReportOption, "Delete Report Option")
            click(clinicalReporter.deleteReportButtonOnDialog, "Confirmation Delete Report Button on Dialog")
            waitTillElementIsNotPresent(clinicalReporter.deletingReportProgressBar, "Deleting Report Progress Bar")
            Thread.sleep(1000)
        }
    }

    def deleteAllClinicalReportsBasedOnReports(){
        boolean deleteReportsFromGAWDTool = false;
        getAllReportIds().each {
            report->
                if(clinicalReporter.statusBasedOnReportId(report).text().trim().equals(WAITING_FOR_APPROVAL) || !clinicalReporter.statusBasedOnReportId(report).text().trim().equals(APPROVED)){
                     deleteReportsFromGAWDTool = true;
                }
                if(!clinicalReporter.statusBasedOnReportId(report).text().trim().equals(WAITING_FOR_APPROVAL) || !clinicalReporter.statusBasedOnReportId(report).text().trim().equals(APPROVED)){
                    clickOnActionsAndValueBasedOnPatientId(report,DELETE_REPORT)
                    click(clinicalReporter.deleteReportButtonOnDialog, "Confirmation Delete Report Button on Dialog")
                    waitTillElementIsNotPresent(clinicalReporter.deletingReportProgressBar, "Deleting Report Progress Bar")
                    Thread.sleep(1000)
                }
        }
        return deleteReportsFromGAWDTool
    }

    def clickItemsPerPageAndChooseValue(String value = HUNDRED) {
        if (filteringprotocol.activePaginatorButton.displayed) {
            scrollToCenter(filteringprotocol.activePaginatorButton)
            click(filteringprotocol.activePaginatorButton, "Paginator Button")
            waitFor { panelBuilder.paginatorDropDownValue(value) }
            scrollToCenter(panelBuilder.paginatorDropDownValue(value))
            click(panelBuilder.paginatorDropDownValue(value), "Drop Down Paginator Value: " + value)
            scrollToCenter(filteringprotocol.newFilteringProtocolButton)
        }
    }

    def getAllReportIds(){
        List reports = new LinkedList()
        clickItemsPerPageAndChooseValue()
        waitFor {clinicalReporter.allReportIds}
        int numberOfReports = clinicalReporter.allReportIds.size()
        for(int i = 0 ;i<numberOfReports;i++){
            reports.add(clinicalReporter.allReportIds[i].text())
        }
        return reports
    }

    def chooseGeneForEachMember(String type) {
        switch (type) {

            case PANEL:
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON).displayed }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON), "Genome of the Affected Person");
                break;

            case SOLO:
                click(clinicalReporter.tabBasedOnRelationship(AFFECTED_PERSON), "Tab Of Affected Person")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON), "Gene of the Affected Person")
                click(clinicalReporter.femaleRadioButton, "Female Radio Button")
                click(clinicalReporter.selectButton, "Select Button");
                break;

            case TRIO:
                click(clinicalReporter.tabBasedOnRelationship(AFFECTED_CHILD), "Tab Of Affected Person")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON).displayed }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON), "Gene of the Affected Person")
                click(clinicalReporter.femaleRadioButton, "Female Radio Button")
                click(clinicalReporter.selectButton, "Select Button")

                waitFor { clinicalReporter.tabBasedOnRelationship(UNAFFECTED_FATHER).displayed }
                click(clinicalReporter.tabBasedOnRelationship(UNAFFECTED_FATHER), "Tab Of Unaffected Father")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_FATHER) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_FATHER), "Gene of the Unaffected Father")
                click(clinicalReporter.selectButton, "Select Button")

                waitFor { clinicalReporter.tabBasedOnRelationship(UNAFFECTED_MOTHER) }
                click(clinicalReporter.tabBasedOnRelationship(UNAFFECTED_MOTHER), "Tab Of Unaffected Mother")
                waitFor { clinicalReporter.modalPopup }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_MOTHER) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_MOTHER), "Gene of the Unaffected Mother")
                click(clinicalReporter.selectButton, "Select Button");
                break;

            case QUAD:
                click(clinicalReporter.tabBasedOnRelationship(AFFECTED_CHILD), "Tab Of Affected Person")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_AFFECTED_PERSON), "Gene of the Affected Person")
                click(clinicalReporter.femaleRadioButton, "Female Radio Button")
                click(clinicalReporter.selectButton, "Select Button")

                waitFor { clinicalReporter.tabBasedOnRelationship(UNAFFECTED_FATHER) }
                click(clinicalReporter.tabBasedOnRelationship(UNAFFECTED_FATHER), "Tab Of Unaffected Father")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_FATHER) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_FATHER), "Gene of the Unaffected Father")
                click(clinicalReporter.selectButton, "Select Button")

                waitFor { clinicalReporter.tabBasedOnRelationship(UNAFFECTED_MOTHER) }
                click(clinicalReporter.tabBasedOnRelationship(UNAFFECTED_MOTHER), "Tab Of Unaffected Mother")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_MOTHER) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_MOTHER), "Gene of the Unaffected Mother")
                click(clinicalReporter.selectButton, "Select Button")

                waitFor { clinicalReporter.tabBasedOnRelationship(UNAFFECTED_SIBLING) }
                click(clinicalReporter.tabBasedOnRelationship(UNAFFECTED_SIBLING), "Tab Of Unaffected Sibling")
                waitFor { clinicalReporter.modalPopup.displayed }
                waitFor { clinicalReporter.selectGeneSpace }
                waitFor { clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_SIBLING) }
                click(clinicalReporter.geneBasedOnRelationship(GENE_OF_UNAFFECTED_SIBLING), "Gene of the Unaffected Sibling")
                click(clinicalReporter.maleRadioButton, "Male Radio Button")
                click(clinicalReporter.selectButton, "Select Button");
                break;
        }
    }
}
