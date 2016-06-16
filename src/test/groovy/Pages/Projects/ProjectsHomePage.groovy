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
            driver.navigate().refresh()
            index++;
            Thread.sleep(1000)
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


    def verifyProjectIsDisplayed(String ProjectName){
        waitFor {projectsHome.projectNameInProjectTable(ProjectName)}
        return projectsHome.projectNameInProjectTable(ProjectName).isDisplayed()
    }

    def getCreatedByTextBasedOnProjectName(String ProjectName){
        return projectsHome.createdByBasedOnProjectName(ProjectName)
    }

    def getDateCreatedBasedOnProjectName(String ProjectName){
        return projectsHome.dateCreatedBasedOnProjectName(ProjectName)
    }

    def getDateModifiedBasedOnProjectName(String ProjectName){
        return projectsHome.dateModifiedBasedOnProjectName(ProjectName)
    }

    def clickAndCheckActiveStatusonPublicProjectsTab(){
        click(projectsHome.publicProjectsTab,"Public Projects tab")
        return projectsHome.publicProjectsTab.parent().has(".active")
    }

    def projectSearch(String ProjectName){
        click(projectsHome.workspaceProjectsTab,"Workspace projects tab")
        type(projectsHome.projectSearchTextField,ProjectName, "Project Search text field")
        click(projectsHome.projectSearchButton,"Project Search button")
    }

    def verifyProjectSearchResults(){
        waitFor {projectsHome.projectsModalSubHeader}
        return projectsHome.projectSearchResults
    }

    def clickOnSearchModalCloseButton(){
        click(projectsHome.searchModalCloseButton,"Search Modal close button")
    }

    def createNewProject(String ProjectName){
        click(projectsHome.newProjectButton,"New Project Button")
        type(projectsHome.projectNameField,ProjectName,"Project Name")
        click(projectsHome.createProjectButton,"Create Project button")
        waitFor {projectsHome.projectSucessfullyCreatedAlert}
        waitFor {projectsHome.closeButton}
        click(projectsHome.closeButton,"Close button")
        Thread.sleep(3000)
    }

    def clickActionsAndOptionBasedOnProject(String ProjectName, String option){
        waitFor { projectsHome.actionsBasedOnProjectName(ProjectName).displayed }
        click(projectsHome.actionsBasedOnProjectName(ProjectName),"Actions button")
        click(projectsHome.optionActionsButton(option),"Action button -> "+ option)
    }

    def editProjectBasedOnProjectName(String ProjectName, String Description){
        waitFor { projectsHome.projectDescriptionTextField}
        type(projectsHome.projectDescriptionTextField,Description,"Project Description text field")
        click(projectsHome.saveButtonInEditProject,"Save button")
        waitFor {projectsHome.projectSavedAlert}
        click(projectsHome.closeButton,"Close button")
    }

    def verifyEditedProjectDetails(String ProjectName){
        waitFor {projectsHome.projectDescBasedOnProjectName(ProjectName)}
        return projectsHome.projectDescBasedOnProjectName(ProjectName)
    }

    def verifyProjectNameOnProjectDetailsPopUp(){
        return projectsHome.projectHeaderName
    }


}
