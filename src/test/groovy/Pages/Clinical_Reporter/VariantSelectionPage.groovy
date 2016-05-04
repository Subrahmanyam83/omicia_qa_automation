package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.VariantSelectionModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 5/2/2016.
 */
class VariantSelectionPage extends BasePage {

    static at = {
        variantSelection.filtersPane.displayed
        variantSelection.resetFilterButton.displayed
        variantSelection.showHideColumnsButton.displayed
        variantSelection.runPhevorButton.displayed
        variantSelection.addSelectedVariantsButton.displayed
        variantSelection.variantInterpretationButton.displayed
        variantSelection.variantsTable.displayed
    }

    static content = {
        variantSelection { module VariantSelectionModule }
    }

    def clickAddSelectedVariants() {
        waitFor { variantSelection.addSelectedVariantsButton.displayed }
        click(variantSelection.addSelectedVariantsButton, "Add Selected Variants Button")
        waitFor { variantSelection.modalPopup.displayed }
        type(variantSelection.addNoteTextField, TEST_NOTE, "Note Text Field on Add Variant Dialog Box")
        click(variantSelection.addVariantButtonInModalPopup, "Add Variant Button on Dialog popup")
        waitFor { variantSelection.crossButtonOnModalDialog.displayed }
        click(variantSelection.crossButtonOnModalDialog, "Cross Button of Dialog popup")
    }

    def clickVariantInterpretationButton() {
        waitFor { variantSelection.variantInterpretationButton.displayed }
        click(variantSelection.variantInterpretationButton, "Variant Interpretation Button");
    }

    def getDefaultColumnNamesForSoloVariantSelection() {
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

    def getChangeBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getChangeBasedOnVariant(variantName, index).displayed }
        return variantSelection.getChangeBasedOnVariant(variantName, index).text().replace("\n", " ")
    }

    def getEffectBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getEffectBasedOnVariant(variantName, index).displayed }
        return variantSelection.getEffectBasedOnVariant(variantName, index).text().replace("\n", " ")
    }

    def getVAASTRankBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getVAASTGeneRank(variantName, index).displayed }
        return Integer.parseInt(variantSelection.getVAASTGeneRank(variantName, index).text().replace("\n", " "))
    }

    def getPhevorRankBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getPhevorGeneRank(variantName, index).displayed }
        return Integer.parseInt(variantSelection.getPhevorGeneRank(variantName, index).text().replace("\n", " "))
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
        Thread.sleep(2000L)
    }

    def clickCheckBoxBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getCheckBoxBasedOnVariant(variantName, index).displayed }
        click(variantSelection.getCheckBoxBasedOnVariant(variantName, index), "Variant Selection Checkbox")
    }

    def verifyIfCheckBoxIsCheckedBasedOnVariant(String variantName, int index = 0) {
        return variantSelection.isCheckboxCheckedBasedOnVariant(variantName, index)
    }


}
