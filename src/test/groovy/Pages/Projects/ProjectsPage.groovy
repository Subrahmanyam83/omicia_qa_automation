package Pages.Projects

import Modules.Projects.ProjectsModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/5/2016.
 */

class ProjectsPage extends BasePage{

    static at = {projects.projectsPageTitleLegend.displayed}

    static content = {
        projects {module ProjectsModule}
    }

    def getNumberOfGenomes(){
        return projects.getNumberOfGenes
    }

    def deleteAllGenomesInProject(){
        if(!projects.noGenomesToDisplay.displayed){
            click(projects.selectAllGenomesCheckBox,"Select All Genes In Projects Page");
            clickActionsMenuAndClickOption("Delete Genomes")
            waitFor {projects.deleteGenomesButton}
            click(projects.deleteGenomesButton,"Delete Genomes Button")
            waitFor { projects.genomesDeletedConfirmationText.displayed }
        }

    }

    def deleteProject(){
        deleteAllGenomesInProject()
        clickActionsMenuAndClickOption("Delete Project");
        click(projects.deleteProjectButton,"Delete Projects Button");
    }

    def clickActionsMenuAndClickOption(String option){
        click(projects.actionsButton,"Projects Page Action Button");
        click(projects.actionsButtonOptions(option),option);
    }

    def clickOnColumnBasedOnGenomeLabel(String genomeLabel, String columnName){
        while(!projects.selectCoumnBasedOnGeneLabel(genomeLabel,columnName)){
            driver.get(driver.currentUrl)
        }
         click(projects.selectCoumnBasedOnGeneLabel(genomeLabel,columnName),columnName)
    }
}
