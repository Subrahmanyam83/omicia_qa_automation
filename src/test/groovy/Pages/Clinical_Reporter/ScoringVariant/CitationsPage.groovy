package Pages.Clinical_Reporter.ScoringVariant

import Modules.Clinical_Reporter.ScoreVariantModule.CitationsModule
import Modules.Clinical_Reporter.ScoreVariantModule.ConditionGeneModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/20/2016.
 */
class CitationsPage extends BasePage {

    static at = {
        conditionGene.activeHeaderTab("Citations")
    }

    static content = {
        citations { module CitationsModule }
        conditionGene { module ConditionGeneModule }
    }

    def clickOnHeaderTab(String tabName) {
        waitFor { conditionGene.headerTab(tabName) }
        click(conditionGene.headerTab(tabName), "Header Tab: " + tabName)
    }

    def addNewCitationsButton(String studyType) {
        waitFor { citations.addNewButtonBasedOnStudyName(studyType) }
        click(citations.addNewButtonBasedOnStudyName(studyType), "Add Citations Button of " + studyType + " Study")
        waitFor { conditionGene.modalPopUp }
        switch (studyType) {
            case COSEGREGATION:
                waitFor { citations.pubMedIDTextField }
                type(citations.pubMedIDTextField, ONE_STRING, "Pub Med ID Text Filed")
                waitFor { citations.pubMedCitationText }
                if (citations.pubMedIDTextField.firstElement().getAttribute("value").equals(ONE_STRING)) {
                    Assert.equals(citations.pubMedCitationText.text().contains("Makar AB, McMartin KE, Palese M, Tephly TR. \"Formate assay in body fluids: application in methanol poisoning..\" Biochemical medicine, Jun 1975"))
                }
                Thread.sleep(1000L)
                type(citations.phenoType, "Test Phenotype", "Phenotype Text Filed")
                type(citations.pedigreeReference, "Pedigree Reference", "Pedigree Reference Text Filed")

                type(citations.affectedWithTextField, "1", "Affected With Text Field")
                type(citations.affectedWithout, "2", "Affected Without Text Field")
                type(citations.unaffectedWithTextField, "3", "Affected With Text Field")
                type(citations.unaffectedWithoutTextField, "4", "Affected With Text Field")
                type(citations.assessmentTextField, "Test Assessment", "Assessment Text Field")

                waitFor { citations.addStudyButton }
                waitFor { citations.addStudyButton.click() }
                waitFor { citations.closeButton }
                click(citations.closeButton, "Close Button of the Modal Popup")
                waitFor { citations.noStudiesRow(studyType).displayed.equals(false) }
                waitFor { citations.studiesListRow(studyType).displayed.equals(true) }
                break;

            case FUNCTIONAL:
                break;

            case GENERAL:
                break;
        }
    }

    def verifyNumberOfCitationsOnCitationsTab() {
        return citations.citationsCountOnTab
    }
}
