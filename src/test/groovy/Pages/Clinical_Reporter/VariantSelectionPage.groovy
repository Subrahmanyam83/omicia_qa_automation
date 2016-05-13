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

    def clickCheckBoxBasedOnVariant(String variantName, int index = 0) {
        waitFor { variantSelection.getCheckBoxBasedOnVariant(variantName, index).displayed }
        click(variantSelection.getCheckBoxBasedOnVariant(variantName, index), "Variant Selection Checkbox")
    }

    def verifyIfCheckBoxIsCheckedBasedOnVariant(String variantName, int index = 0) {
        return variantSelection.isCheckboxCheckedBasedOnVariant(variantName, index)
    }
}
