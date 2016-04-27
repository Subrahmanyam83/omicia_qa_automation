package Pages.Projects

import Modules.Projects.VariantReportModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 4/22/2016.
 */
class VariantReportPage extends BasePage{

    static at = {
        $(".variants.paginator.table.table-condensed.table-bordered.table-striped").isDisplayed()
        $(".accordion.accordion-menu").isDisplayed()
        $("#report-variants").displayed
    }

    static content = {
        variantReport {module VariantReportModule}
    }

    def getNumberOfGenesOnVariantPage(){
        return variantReport.numberOfGenes
    }

    def clickOnColumnBasedOnGene(String geneName,String columnName){
        while(!variantReport.columnNameBasedOnGene(geneName,columnName).isDisplayed()){
            driver.get(driver.currentUrl)
        }
        click(variantReport.columnNameBasedOnGene(geneName,columnName),columnName+" of the GENE: "+geneName)
    }

    def verifyDialogBoxPresentOnClickAndClose(){
        waitFor("fast") {variantReport.dialogBox.isDisplayed()}
        click(variantReport.closeButtonOfDialogBox,"Close button of the Dialog Box")
    }

}
