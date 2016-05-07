package Pages.Projects

import Modules.Clinical_Reporter.VariantSelectionModule
import Modules.Projects.ReportsHomeModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 5/6/2016.
 */
class ReportsHomePage extends BasePage {

    static at = {
        reportsHome.reportsPageVariantsTable
        variantSelection.filtersPane.displayed
        variantSelection.resetFilterButton.displayed
    }

    static content = {
        reportsHome { module ReportsHomeModule }
        variantSelection { module VariantSelectionModule }
    }

    def getNumberOfItems() {
        return Integer.parseInt(variantSelection.numberOfItems.text().replace(" Items", ""))
    }

    def clickOnHeaderButton(String buttonName) {
        waitFor { reportsHome.headerButton(buttonName) }
        click(reportsHome.headerButton(buttonName), "Header Button: " + buttonName)
    }

    def verifyReportHeading() {
        return reportsHome.header().text().split("\n")[0]
    }

    def waitForThePageToLaunch() {
        while (!$(".total", text: "Items").size().equals(ZERO)) {
            Thread.sleep(1000)
        }
    }
}
