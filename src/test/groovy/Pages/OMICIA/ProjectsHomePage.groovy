package Pages.OMICIA

import Modules.OMICIA.HeaderModule
import Modules.OMICIA.ProjectsHomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/5/2016.
 */
class ProjectsHomePage extends BasePage{

    static at = {projectsHome.projectNameColumn.displayed}

    static content = {
        projectsHome {module ProjectsHomeModule}
        header {HeaderModule}
    }

    def getGenomeBasedOnProjectName(String projectName){
        return Integer.parseInt(projectsHome.genomeCountBasedOnProjectName(projectName).toString())
    }

    def clickProjectInProjectsHomePage(String projectName){
        click(projectsHome.projectButton(projectName),"Project Button in Projects Page")
    }

    def getNumberOfGenomes(){
        return projectsHome.getNumberOFGenes
    }

    def deleteProject(String projectName){
        clickProjectInProjectsHomePage(projectName)
        at ProjectsPage
        clickActionsMenuAndClickOption("Delete Genomes")


    }
}
