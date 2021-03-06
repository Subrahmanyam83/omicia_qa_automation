package Pages.Projects

import Modules.Projects.ProjectsModule
import Utilities.Class.BasePage
import org.testng.Assert

/**
 * Created by in02183 on 4/5/2016.
 */

class ProjectsPage extends BasePage{

    static at = {projects.projectsPageTitleLegend.displayed}

    static content = {
        projects {module ProjectsModule}
    }

    def getNumberOfGenes() {
        return projects.getNumberOfGenes
    }

    def deleteAllGenomesInProject(){
        if(!projects.noGenomesToDisplay.displayed){
            click(projects.selectAllGenomesCheckBox,"Select All Genes In Projects Page");
            clickActionsMenuAndClickOption("Delete Genomes")
            waitFor {projects.deleteGenomesButton}
            click(projects.deleteGenomesButton,"Delete Genomes Button")
            waitFor { projects.closeButtonOnDialogPopup }
            click(projects.closeButtonOnDialogPopup,"Close Button on Modal Dialog Popup")
            waitFor {projects.noGenomesToDisplay}
        }
    }

    def deleteProjects() {
        deleteAllGenomesInProject()
        clickActionsMenuAndClickOption("Delete Project");
        click(projects.deleteProjectButton,"Delete Projects Button");
    }

    def clickActionsMenuAndClickOption(String option){
        waitFor {projects.actionsButton}
        click(projects.actionsButton,"Projects Page Action Button");
        waitFor {projects.actionsButtonOptions(option)}
        click(projects.actionsButtonOptions(option),option);
    }

    def clickOnColumnBasedOnGenomeLabel(String genomeLabel, String columnName){
        int index = 0;
        while (!projects.selectColumnBasedOnGeneLabel(genomeLabel, columnName)) {
            driver.navigate().refresh()
            index++;
            Thread.sleep(500)
            if (index.equals(FIFTY)) {
                Assert.fail("Pipeline is busy or Down: : 'Refreshing Page is not showing link on Projects Page'")
            }
        }
        click(projects.selectColumnBasedOnGeneLabel(genomeLabel, columnName), columnName)
    }

    def waitTillAllVariantReportsAreAvailable() {
        int index = 0;
        while (!projects.availableVariantReports.equals(projects.numberOfVariantReports)) {
            driver.navigate().refresh()
            index++;
            Thread.sleep(1000L)
            if (index.equals(FIFTY)) {
                Assert.fail("Pipeline is busy or Down:: 'Refreshing Projects Page is not making Variant Reports available'")
            }
        }
    }

    def launchAppAndChooseValue(String value) {
        waitFor { projects.launchApp }
        click(projects.launchApp, "Launch App Button")
        waitFor { projects.dropDownMenuValue(value) }
        click(projects.dropDownMenuValue(value), "Launch App Drop Down Value")
    }

    def clickOnReport(String reportType) {
        waitFor { projects.variantReportType(reportType) }
        click(projects.variantReportType(reportType), "Variant Report Type")
    }

    def deleteReport(String reportName,int index = 0){
        waitFor {projects.reportLink(reportName,index)}
        waitFor {projects.deleteButtonAgainstReport(reportName,index)}
        click(projects.deleteButtonAgainstReport(reportName,index),"Delete Button on Report Name: "+reportName)
        waitFor {projects.deleteReportButton}
        click(projects.deleteReportButton,"Delete Report Button on Modal Dialog Popup")
        waitTillElementIsNotPresent(projects.fadedBackgroundWindow,"Transparent background Window")
        waitFor {projects.projectNameFromHeader.displayed}
    }

    def getProjectNameFromHeader(){
        waitFor {projects.projectNameFromHeader}
        return projects.projectNameFromHeader.text().replace("Project: ","").trim()
    }

    def getNumberOfReports(){
        waitFor {projects.reportsUnderReportType.displayed}
        return projects.reportsUnderReportType.size()
    }
}
