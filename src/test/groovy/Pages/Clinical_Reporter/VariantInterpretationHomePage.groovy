package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.VariantInterpretationHomeModule
import Modules.Clinical_Reporter.VariantSelectionModule
import Utilities.Class.BasePage
import org.openqa.selenium.interactions.Actions

/**
 * Created by E002183 on 4/26/2016.
 */
class VariantInterpretationHomePage extends BasePage {

    static at = {
        interpretVariantsHome.variantTable.displayed
    }

    static content = {
        interpretVariantsHome { module VariantInterpretationHomeModule }
        variantSelection { module VariantSelectionModule }
    }

    def getDefaultColumnNamesOnPage() {
        List<String> columnNames = new LinkedList<String>()
        for (int i = 0; i < variantSelection.variantTableColumnText.size(); i++) {
            if (variantSelection.variantTableColumnText[i].text() != "") {
                columnNames.add(variantSelection.variantTableColumnText[i].text().replace("\n", " "))
            }
        }
        return columnNames.sort()
    }

    /*This will get the number of Items on the Variants Page*/
    def getNumberOfItems() {
        return Integer.parseInt(variantSelection.numberOfItems.text().replace(" Items", ""))
    }

    def getPositionDBSNPBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getPositionDBSNAP(variantName, index).displayed }
        return variantSelection.getPositionDBSNAP(variantName, index).text().replace("\n", " ")
    }

    def getChangeBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getChangeBasedOnVariant(variantName, index).displayed }
        return variantSelection.getChangeBasedOnVariant(variantName, index).text().replace("\n", " ")
    }

    def getEffectBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getEffectBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getEffectBasedOnVariant(variantName, index).text().replaceAll("\n", " ")
    }

    def getClassBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getClassBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getClassBasedOnVariant(variantName, index).text().trim()
    }

    def getClassConditionBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getClassCondition(variantName, index).displayed }
        return interpretVariantsHome.getClassCondition(variantName, index).text().trim()
    }

    def getScoringStatusBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getScoringStatus(variantName, index).displayed }
        return interpretVariantsHome.getScoringStatus(variantName, index).text().trim()
    }

    def getReportSectionBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getReportSection(variantName, index).displayed }
        return interpretVariantsHome.getReportSection(variantName, index).text().trim()
    }

    def getLatestClassificationBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getLatestClassification(variantName, index).displayed }
        return interpretVariantsHome.getLatestClassification(variantName, index).text().trim()
    }

    def getStatusBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getStatusBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getStatusBasedOnVariant(variantName, index).text().trim()
    }

    def getReportBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getReportSectionBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getReportSectionBasedOnVariant(variantName, index).text().trim()
    }

    def getEvidenceBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getEvidenceBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getEvidenceBasedOnVariant(variantName, index).text().trim()
    }

    def getPhevorRankBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getPhevorGeneRank(variantName, index).displayed }
        return Integer.parseInt(variantSelection.getPhevorGeneRank(variantName, index).text().replace("\n", " "))
    }

    def getInheritanceMode(String variantName, int index = 0) {
        waitFor { variantSelection.getInheritanceMode(variantName, index).displayed }
        return variantSelection.getInheritanceMode(variantName, index).text().replace("\n", " ")
    }

    def getVVPCADDBasecOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVVPCADD(variantName, index).displayed }
        return variantSelection.getVVPCADD(variantName, index).text().replace("\n", " ")
    }

    def getVAASTGeneRankBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVAASTGeneRank(variantName, index).displayed }
        return Integer.parseInt(variantSelection.getVAASTGeneRank(variantName, index).text().trim())
    }

    def getVAASTRankBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVAASTRank(variantName, index).displayed }
        return Integer.parseInt(variantSelection.getVAASTRank(variantName, index).text().replace("\n", " "))
    }

    def getVAASTVScoreBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVAASTVScore(variantName, index).displayed }
        return variantSelection.getVAASTVScore(variantName, index).text().replace("\n", " ")
    }

    def getVAASTGScoreBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVAASTGScore(variantName, index).displayed }
        return variantSelection.getVAASTGScore(variantName, index).text().replace("\n", " ")
    }

    def clickOnInterpretVariantBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.interpretVariantLinkBasedOnVariant(variantName, index).displayed }
        click(interpretVariantsHome.interpretVariantLinkBasedOnVariant(variantName, index), "Interpret Variant of the Variant: '" + variantName + "'")
    }

    def getVariantIdBasedOnVariantName(String variantName, int index = 0) {
        return interpretVariantsHome.getVariantIdWithIndexBasedOnVariantName.toString().trim()
    }

    def runPhevor(String textFieldValue) {
        waitFor { variantSelection.runPhevorButton.displayed }
        waitFor { variantSelection.runPhevorButton.click() }
        waitFor { variantSelection.phevorTextField.displayed }
        type(variantSelection.phevorTextField, textFieldValue, "Run Phevor Text Field")
        waitFor { variantSelection.dropDownValue(textFieldValue) }
        click(variantSelection.dropDownValue(textFieldValue), "Drop Down Value-> " + textFieldValue)

        waitFor { variantSelection.runButtonOnPhevor.displayed }
        click(variantSelection.runButtonOnPhevor, "Run Phevor Button on dialog")

        waitTillElementIsNotPresent(variantSelection.runingPhevorProgressBar, "Running Phevor Progress Bar")
        waitTillElementIsNotPresent(variantSelection.phevorProgressBar, " Phevor Progress Bar")
        Thread.sleep(2000L)
    }

    def showHideColumns(String columnName, boolean OnOff = true) {
        waitFor { interpretVariantsHome.showHideColumnButton.displayed }
        click(interpretVariantsHome.showHideColumnButton, "Show Hide Column")
        if (OnOff.equals(true)) {
            if (!interpretVariantsHome.ifCheckBoxIsChecked(columnName).equals("true")) {
                click(interpretVariantsHome.checkBoxBasedOnColumnName(columnName), "Checkbox against the column: " + columnName)
            }
        } else {
            if (interpretVariantsHome.ifCheckBoxIsChecked(columnName).equals("true")) {
                click(interpretVariantsHome.checkBoxBasedOnColumnName(columnName), "Checkbox against the column: " + columnName)
            }
        }
        click(interpretVariantsHome.updateShowHideColumnsButton, "Update button of Show Hide Column Modal Dialog")
    }

    def clickReviewReport() {
        waitFor { interpretVariantsHome.reviewReportButton.displayed }
        click(interpretVariantsHome.reviewReportButton, "Review Report Button")
    }

    def openScoreVariantsBasedOnVariantName(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.geneNameLink(variantName, index) }
        interact { moveToElement(interpretVariantsHome.geneNameLink(variantName, index)) }
        new Actions(driver).moveToElement(interpretVariantsHome.rowBasedOnVariant(variantName, index).firstElement(), 0, 0).moveByOffset(1, 1).click().build().perform()
        waitFor { interpretVariantsHome.scoreVariantsLink }
        click(interpretVariantsHome.scoreVariantsLink, "Score Variant Link on the " + index + " " + variantName + " Variant")
    }
}
