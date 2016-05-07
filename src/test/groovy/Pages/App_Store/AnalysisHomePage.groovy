package Pages.App_Store

import Modules.App_Store.AnalysisHomeModule
import Modules.Clinical_Reporter.ClinicalReporterModule
import Utilities.Class.BasePage

/**
 * Created by E002183 on 5/5/2016.
 */
class AnalysisHomePage extends BasePage {

    static at = {
        analysisHome.runButton
        analysisHome.selectGenesTab
    }

    static content = {
        analysisHome { module AnalysisHomeModule }
        clinicalReporter { module ClinicalReporterModule }
    }

    def clickOnRun() {
        waitFor { analysisHome.runButton }
        click(analysisHome.runButton, "Analysis Run Button")
    }
}
