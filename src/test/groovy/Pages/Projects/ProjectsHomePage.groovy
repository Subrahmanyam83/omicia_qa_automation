package Pages.Projects

import Modules.Login.HeaderModule
import Modules.Projects.ProjectsHomeModule
import Utilities.Class.BasePage
import org.testng.Assert

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

    /*def deleteProject(String projectName){
        clickProjectInProjectsHomePage(projectName)
        at ProjectsPage
        clickActionsMenuAndClickOption("Delete Genomes")
    }*/

    def refreshTillCountMatches(String NewProjectName,int count){
        int index = 0;
        while(getGenomeBasedOnProjectName(NewProjectName)!=(count)){
            driver.get(driver.currentUrl);
            index++;
            Thread.sleep(500)
            if (index.equals(FIFTY)) {
                Assert.fail("Pipeline is busy or Down: 'Refreshing Page is not making the Gene Count increase On Projects Home Page'")
            }
        }
    }

    def getAllProjectNames(){
        List projectNames = new LinkedList();
        if(!projectsHome.numberOfProjects.equals(ZERO)) {
            for (int i = 0; i < projectsHome.eachProject.size(); i++) {
                projectNames.add(projectsHome.eachProject[i].text())
            }
        }
        return projectNames
    }
}
