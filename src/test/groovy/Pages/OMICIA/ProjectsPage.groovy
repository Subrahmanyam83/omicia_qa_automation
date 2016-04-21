package Pages.OMICIA

import Modules.OMICIA.ProjectsModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsPage extends BasePage{

    static at = {projects.projectsPageTitleLegend.displayed}

    static content = {
        projects {module ProjectsModule}
    }

    def deleteAllGenomesInProject(){
        click(projects.selectAllGenomesCheckBox,"Select All Genes In Projects Page");
        clickActionsMenuAndClickOption("Delete Genomes")
        waitFor {projects.deleteGenomesButton}
        click(projects.deleteGenomesButton,"Delete Genomes Button")
        waitFor {projects.genomesDeletedConfirmationText}
    }

    def deleteProject(){
        deleteAllGenomesInProject()
        clickActionsMenuAndClickOption("Delete Project");
        click(projects.deleteProjectButton,"Delete Projects Button");
    }

    def clickActionsMenuAndClickOption(String option){
        click(projects.actionsButton,"Projects Page Action Button");
        click(projects.actionsButtonOptions(option),"Actions Button");

    }
}
