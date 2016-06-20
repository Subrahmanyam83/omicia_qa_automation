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

    def projectSearch(String SearchString){
        click(projectsHome.workspaceProjectsTab,"Workspace projects tab")
        type(projectsHome.projectSearchTextField,SearchString, "Project Search text field")
        click(projectsHome.projectSearchButton,"Project Search button")
    }

    def verifyProjectSearchResults(String searchString){
        waitFor {projectsHome.modalPopUp}
        if(!projectsHome.noSearchResults){
            if(projectsHome.projectsSubHeaderInSearchModal){
                for(int i =0;i<projectsHome.projectSearchResultsCount.size();i++) {
                    if (!projectsHome.projectSearchResultsCount[i].text().contains(searchString)) {
                        return false
                    }
                    return true
                }
            }else if(projectsHome.genomeSubHeaderInSearchModal){

            }

        }else{

            click(projectsHome.modalCloseButton, "Close Button")
            return projectsHome.noSearchResults.text().trim()
        }
    }


    def clickOnSearchModalCloseButton(){
        click(projectsHome.modalCloseButton,"Search Modal close button")
    }

    def clickOnNewProjectButton(){
        click(projectsHome.newProjectButton,"New Project Button")
    }

    def createOrEditProject(String projectName,String description,boolean isShareProjectCheckboxRequired, boolean isProjectNameRequired, boolean isProjectDescriptionRequired, boolean isContributorsRole, String action){
        waitFor {projectsHome.projectNameField}
        if(isProjectNameRequired){
            projectsHome.projectNameField.firstElement().clear()
            type(projectsHome.projectNameField,projectName,"Project Name")
        }
        if(isProjectDescriptionRequired){
            projectsHome.projectDescriptionTextField.firstElement().clear()
            type(projectsHome.projectDescriptionTextField,description,"Project Description")
        }
        if(isShareProjectCheckboxRequired){
            if(isContributorsRole){
                click(projectsHome.contributorsRole, "As Contributors radio button")
            }
            click(projectsHome.shareProjectCheckbox,"Share Project checkbox")
        }
        if(action.equals("Create Project")||action.equals("Save")) {
            waitFor {projectsHome.optionBasedOnCreateOrEditProject(action)}
            click(projectsHome.optionBasedOnCreateOrEditProject(action), "Action button -> " + action)
            waitFor { projectsHome.modalCloseButton }
            click(projectsHome.modalCloseButton)
        }
        if(action.equals("Cancel")){
            waitFor {projectsHome.optionBasedOnCreateOrEditProject(action)}
            click(projectsHome.optionBasedOnCreateOrEditProject(action), "Action button -> "+action)
        }

    }

    def clickActionsAndOptionBasedOnProject(String ProjectName, String option){
        waitFor { projectsHome.actionsBasedOnProjectName(ProjectName).displayed }
        click(projectsHome.actionsBasedOnProjectName(ProjectName),"Actions button")
        click(projectsHome.optionActionsButton(option),"Action button -> "+ option)
    }

    def verifyEditedProjectDetails(String ProjectName){
        waitFor {projectsHome.projectDescBasedOnProjectName(ProjectName)}
        return projectsHome.projectDescBasedOnProjectName(ProjectName)
    }

    def verifyProjectNameOnProjectDetailsPopUp(){
        return projectsHome.projectHeaderName.trim()
    }

    def verifyProjectDescOnProjectDetailsPopUp(){
        return projectsHome.projectDescription.replaceAll("\n"," ").trim()
    }

    def verifyProjectContributorsOnProjectDetailsPopUp(){
        return projectsHome.projectContributors.trim()
    }

    def clickCloseOnProjectDetailsPopUp(){
        waitFor {projectsHome.modalCloseButton}
        click(projectsHome.modalCloseButton,"Close Button")
    }

}
