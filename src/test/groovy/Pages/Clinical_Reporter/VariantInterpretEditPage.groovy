package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.VariantInterpretEditModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/27/2016.
 */
class VariantInterpretEditPage extends BasePage {

    static at = {
        interpretVariant.interpretVariantModalWindow.displayed
    }

    static content = {
        interpretVariant { module VariantInterpretEditModule }
    }

    def clickOnTab(String tabName) {
        waitFor { interpretVariant.tab(tabName) }
        click(interpretVariant.tab(tabName), "Interpret Variant Tab Name: '" + tabName + "'")
        waitFor { interpretVariant.currentActiveTab(tabName) }
    }

    def editVariant(String classification, String report, String description = "_Test Description for Interpret Variation", String condition = "Ataxia", String citation = "123", String status = "Reviewed", String note = "Test Note for Finding") {
        waitFor { interpretVariant.variantClassificationDropDown }
        click(interpretVariant.variantClassificationDropDown, "Variant Classification Drop Down")
        click(interpretVariant.dropDownValue(classification), "Variant Classification Drop Down Value: " + classification);

        type(interpretVariant.conditionTextField, condition, "Condition Text Field");

        type(interpretVariant.citationTextField, citation, "Citation Text Field")
        click(interpretVariant.citationAddButton, "Citation Add Button")
        waitFor { interpretVariant.citationAddedConfirmation.displayed }

        type(interpretVariant.description, description, "Interpret Variation Description")

        click(interpretVariant.statusDropDown, "Status Drop Down")
        click(interpretVariant.dropDownValue(status), "Status Drop Down Value" + status)

        click(interpretVariant.reportDropDown, "Report Drop Down")
        click(interpretVariant.dropDownValue(report), "Report Drop Down Value" + report)

        type(interpretVariant.notesTextField, note, "Note Text Field")
        click(interpretVariant.addNoteButton, "Add Note Button")
        waitFor { interpretVariant.noteHeader.displayed }
    }

    def getCurrentVariantClassificationDropDownValue() {
        return interpretVariant.currentVariantClassificationDropDownValue.trim()
    }

    def getCurrentConditionTextFieldValue() {
        return interpretVariant.conditionTextField.value().toString().trim()
    }

    def saveVariant() {
        click(interpretVariant.saveButton, "Save Variant")
        Thread.sleep(2000)
    }

    def closeInterpretVariantDialog() {
        waitFor { interpretVariant.closeButton }
        click(interpretVariant.closeButton, "Close Button on Interpret Variant Dialog")
    }

    def clickOnCopyToVariantInterpretationAndDescription() {
        waitFor { interpretVariant.copyToVariantInterpretationAndDescritpion.displayed }
        click(interpretVariant.copyToVariantInterpretationAndDescritpion, "Copy to Variant Interpretation and Description Button")
        waitFor { interpretVariant.currentActiveTab(EDIT_VARIANT) }
    }
}
