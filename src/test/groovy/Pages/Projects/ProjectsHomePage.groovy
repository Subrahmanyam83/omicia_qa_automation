package Pages.Projects

import Modules.Login.HeaderModule
import Modules.Projects.ProjectsHomeModule
import Utilities.Class.BasePage
import org.openqa.selenium.Keys
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
        waitFor {projectsHome.projectButton(projectName)}
        click(projectsHome.projectButton(projectName),"Project Button in Projects Page")
    }

    def getNumberOfProjects(){
        return projectsHome.numberOfProjects
    }

    def clickOnProjectFolderIcon(int index = 0){
        waitFor {projectsHome.projectIconFolder(index)}
        click(projectsHome.projectIconFolder(index),index +1 +" Project Folder Icon on Projects Home Page")
    }

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
        return projectsHome.projectNameInProjectTable(ProjectName).displayed
    }

    def getProjectDescriptionBasedOnProjectName(String ProjectName){
        return projectsHome.projectDescriptionBasedOnProjectName(ProjectName)
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

    def clickOnTab(String tabName){
        waitFor {projectsHome.projectsTab(tabName)}
        click(projectsHome.projectsTab(tabName),tabName+" Projects tab")
        waitFor {projectsHome.activeProjectTab(tabName)}
    }

    def projectSearch(String SearchString){
        projectsHome.projectSearchTextField.firstElement().clear()
        type(projectsHome.projectSearchTextField,SearchString, "Project Search text field")
        click(projectsHome.projectSearchButton,"Project Search button")
    }

    def verifyProjectSearchResults(String searchString){
        boolean isSearchResultPresent = false
        waitFor {projectsHome.modalPopUp}
        List searchResults = new ArrayList()
        if(projectsHome.noSearchResults.text().contains("Projects")) {
            isSearchResultPresent = true
            int projectSize = projectsHome.projectSearchResultsCount.size()
            for (int i = 0; i < projectSize; i++) {
                searchResults.add(projectsHome.projectSearchResults(i))
            }
        }
        if(projectsHome.noSearchResults.text().contains("Genomes")){
            isSearchResultPresent = true
            int genomeSize = projectsHome.genomeSearchResultsCount.size()
            for(int i =0;i<genomeSize;i++) {
                    searchResults.add(projectsHome.genomeSearchResults(i))
            }
        }
        if(isSearchResultPresent.equals(false)){
            searchResults.add(projectsHome.noSearchResults.text().trim())
            click(projectsHome.modalCloseButton,"Modal close button")
            return searchResults
        }

        click(projectsHome.modalCloseButton,"Modal close button")
        return searchResults.sort()
    }

    def clickOnModalCloseIcon(){
        waitFor {projectsHome.modalCloseIcon}
        click(projectsHome.modalCloseIcon,"Modal close Icon")
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
                click(projectsHome.shareProjectCheckbox,"Share Project checkbox")
                click(projectsHome.contributorsRole, "As Contributors radio button")
            }else {
                click(projectsHome.shareProjectCheckbox, "Share Project checkbox")
            }
        }
        if(action.equals("Create Project")||action.equals("Save")) {
            waitFor {projectsHome.optionBasedOnCreateOrEditOrShareProject(action)}
            click(projectsHome.optionBasedOnCreateOrEditOrShareProject(action), "Action button -> " + action)
            waitFor { projectsHome.modalCloseButton }
            click(projectsHome.modalCloseButton,"Close button")
        }
        if(action.equals("Cancel")){
            waitFor {projectsHome.optionBasedOnCreateOrEditOrShareProject(action)}
            click(projectsHome.optionBasedOnCreateOrEditOrShareProject(action), "Action button -> "+action)
        }
    }

    def clickActionsAndOptionBasedOnProject(String ProjectName, String option){
        waitFor { projectsHome.actionsBasedOnProjectName(ProjectName).displayed }
        click(projectsHome.actionsBasedOnProjectName(ProjectName),"Actions button")
        Thread.sleep(2000)
        click(projectsHome.optionActionsButton(option),"Action button -> "+ option)
    }

    def verifyProjectNameOnProjectDetailsPopUp(){
        waitFor {projectsHome.projectHeaderName}
        return projectsHome.projectHeaderName.replaceAll("Project:"," ").trim()
    }

    def verifyProjectDescOnProjectDetailsPopUp(){
        return projectsHome.projectDescription.replaceAll("\n"," ").trim()
    }

    def verifyProjectContributorsOnProjectDetailsPopUp(){
        return projectsHome.projectContributors.trim()
    }

    def shareProjectWithUser(List userNames, String roleoption, String option) {
        waitFor {projectsHome.selectUserDropDown}
        click(projectsHome.selectUserDropDown,"Select user dropdown")
        userNames.each{
            userName-> click(projectsHome.userDropDownOption(userName),"Select the user")
            click(projectsHome.shareButton,"Share button")
            waitFor {projectsHome.projectRoleButton(userName,roleoption)}
            if(roleoption.equals("Viewer")){
                click(projectsHome.optionBasedOnCreateOrEditOrShareProject(option),"Select Viewer Option")
            }else{
                click(projectsHome.projectRoleButton(userName,roleoption),"Select Contributor role")
                click(projectsHome.optionBasedOnCreateOrEditOrShareProject(option),"Select Save Changes option")
            }
        }
        waitFor {projectsHome.shareChangesSavedAlert}
        click(projectsHome.modalCloseButton,"Close button")
    }

    def getActionsListBasedOnProject(String projectName){
        waitFor {projectsHome.actionsBasedOnProjectName(projectName)}
        click(projectsHome.actionsBasedOnProjectName(projectName),"Actions Button")
        List actionsList = new ArrayList()
        for(int i=0;i<projectsHome.actionsCountBasedOnProjectRole.size();i++){
                actionsList.add(projectsHome.actionsListBasedOnProjectRole(i))
        }
        click(projectsHome.actionsBasedOnProjectName(projectName),"Actions Button")
        return actionsList
    }

    def removeAccessFlow(String option){
        waitFor {projectsHome.optionBasedOnCreateOrEditOrShareProject(option)}
        click(projectsHome.optionBasedOnCreateOrEditOrShareProject(option),"Remove Project button")
        waitFor {projectsHome.projectAccessRemovedAlert}
        waitFor {projectsHome.newProjectButton}
    }

    def checkIfUserIsNotDisplayedOnShareProjectModal(String userName){
        waitFor {projectsHome.shareButton}
        for(int i=0;i<projectsHome.numberOfUsersInShareProjectModal.size();i++){
            if(projectsHome.eachUserNameInShareProjectModal(i).text().trim().equals(userName)){
                return false
            }
        }
        return true
    }

    def verifyProjectIsNotDisplayed(String ProjectName){
        waitFor {projectsHome.projectsTab(WORKSPACE_PROJECTS)}
        for(int i=0;i<projectsHome.numberOfProjects;i++){
            if(projectsHome.eachProjectNameText(i).text().trim().equals(ProjectName)){
                return false
            }
        }
        return true
    }

    def openNewPrivateWindow(){
        if(System.getProperty("browser").equals("chrome")){
            $("html body") << Keys.chord(Keys.CONTROL,Keys.SHIFT,"n")
        }else{
            $("html body") << Keys.chord(Keys.CONTROL,Keys.SHIFT,"p")
        }
    }
}
