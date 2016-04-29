package Pages.Clinical_Reporter

import Modules.Clinical_Reporter.InterpretVariantsHomeModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/26/2016.
 */
class InterpretVariantsHomePage extends BasePage {

    static at = {
        interpretVariantsHome.variantTable.displayed
    }

    static content = {
        interpretVariantsHome { module InterpretVariantsHomeModule }
    }

    /**/
    def getEffectBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getEffectBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getEffectBasedOnVariant(variantName, index).text().trim()
    }

    def getClassBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getClassBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getClassBasedOnVariant(variantName, index).text().trim()
    }

    def getStatusBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getStatusBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getStatusBasedOnVariant(variantName, index).text().trim()
    }

    def getReportBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.getReportSectionBasedOnVariant(variantName, index).displayed }
        return interpretVariantsHome.getReportSectionBasedOnVariant(variantName, index).text().trim()
    }

    def clickOnInterpretVariantBasedOnVariant(String variantName, int index = 0) {
        waitFor { interpretVariantsHome.interpretVariantLinkBasedOnVariant(variantName, index) }
        click(interpretVariantsHome.interpretVariantLinkBasedOnVariant(variantName, index), "Interpret Variant of the Variant: '" + variantName + "'")
    }

    def getVariantIdBasedOnVariantName(String variantName, int index = 0) {
        return interpretVariantsHome.getVariantIdWithIndexBasedOnVariantName.toString().trim()
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
}
