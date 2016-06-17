package Pages.Projects

import Modules.Clinical_Reporter.VariantSelectionModule
import Modules.Projects.VariantReportModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by E002183 on 5/6/2016.
 */
class VariantReportPage extends BasePage {

    static at = {
        variantReport.reportsPageVariantsTable
        variantSelection.filtersPane.displayed
        variantSelection.resetFilterButton.displayed
    }

    static content = {
        variantReport { module VariantReportModule }
        variantSelection { module VariantSelectionModule }
    }

    def getNumberOfItems() {
        return Integer.parseInt(variantSelection.numberOfItems.text().replace(" Items", ""))
    }

    def clickOnHeaderButton(String buttonName) {
        waitFor { variantReport.headerButton(buttonName) }
        click(variantReport.headerButton(buttonName), "Header Button: " + buttonName)
    }

    def getReportHeading() {
        return variantReport.header().text().split("\n")[0]
    }

    def waitForTheReportToAppearWithNoOpacity(){
        waitFor {variantReport.reportTableAppear}
    }

    def getNumberOfGenesOnVariantPage(){
        return variantReport.numberOfGenes
    }

    def clickOnColumnBasedOnGene(String geneName,String columnName){
        int index = 0;
        while(!variantReport.columnNameBasedOnGene(geneName,columnName).isDisplayed()){
            driver.navigate().refresh()
            index++;
            if (index.equals(FIFTY)) {
                Assert.fail("Pipeline is busy or Down: 'Refreshing Page is not showing link on Projects Page'")
            }
        }
        click(variantReport.columnNameBasedOnGene(geneName,columnName),columnName+" of the GENE: "+geneName)
    }

    def verifyDialogBoxPresentOnClickAndClose(){
        waitFor("fast") {variantReport.dialogBox.isDisplayed()}
        click(variantReport.closeButtonOfDialogBox,"Close button of the Dialog Box")
    }

    def verifyContentOnVAASTViewerPage(){
        waitFor {variantReport.yAxisLabel}
        waitFor {variantReport.yAxisLabel.text().equals(VAAST_SCORE)}

        waitFor {variantReport.xAxisLabel}
        waitFor {variantReport.xAxisLabel.text().equals(CHROMOSOME)}

        waitFor {variantReport.vaastPlotOverview}
    }
}
