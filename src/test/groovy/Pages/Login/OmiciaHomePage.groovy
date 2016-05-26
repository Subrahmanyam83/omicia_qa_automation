package Pages.Login

import Modules.Login.HeaderModule
import Modules.Login.HomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class OmiciaHomePage extends BasePage{

    static at = {
        header.homePageHeaderOmiciaText.displayed
    }

    static content = {
        home {module HomeModule}
        header {module HeaderModule}
    }

    def openTab(String tabName){
        click(home.homePageTab(tabName),tabName)
    }

    def openUploadGenomes(){
        waitFor {home.uploadGenomesButton}
        click(home.uploadGenomesButton,"Upload Genome Button");
    }

    def openProjects(){
        click(home.projectsButton,"Projects Button");
    }

    def switchWorkspace(String workspace) {
        waitFor { home.workSpaceDropDown }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        waitFor { home.workSpaceDropDownValue(workspace) }
        click(home.workSpaceDropDownValue(workspace), "Workspace Value on Drop Down")
        waitFor { home.switchWorkspaceAlert("Selected workspace: " + workspace) }
    }

    def getWorkspaceIdBasedOnName(String workSpaceName) {
        click(home.workSpaceDropDown, "Workspace Drop Down")
        String workspaceId = home.workspaceIdBasedOnWorkspaceName(workSpaceName)
        click(home.workSpaceDropDown, "Workspace Drop Down")
        return workspaceId
    }

    def getNamesOfAllWorkSpaces() {
        waitFor { home.workSpaceDropDown }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        int numberOfWorkspaces = home.numberOfWorkSpacesRows.size();
        List workspaceNames = new ArrayList()
        for (int i = 0; i < numberOfWorkspaces; i++) {
            workspaceNames.add(home.numberOfWorkSpacesRows[i].text())
        }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        return workspaceNames
    }

    def createNewWorkspace(String workspaceName, boolean switchworkspace = false) {
        waitFor { home.workSpaceDropDown }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        waitFor { home.createNewWorkspaceLink }
        click(home.createNewWorkspaceLink, "Create New Workspace Button")

        waitFor { home.createNewWorkspaceButton }
        type(home.workspaceNameTextField, workspaceName, "WorkSpace Name Text Field")
        click(home.createNewWorkspaceButton, "Create New Workspace Button")
        waitFor { home.returnToListButton }
        click(home.returnToListButton, "Return to List Button")
        waitFor { home.newWorkspaceButton }
        changePaginatorLevel()
        waitFor { home.workSpaceRow(workspaceName) }
        if (!switchworkspace.equals(false)) {
            switchWorkspace(workspaceName)
        }
        waitFor { header.omiciaOpalHomePage }
        click(header.omiciaOpalHomePage, "Header Logo")
    }

    def changePaginatorLevel(String value = HUNDRED) {
        Thread.sleep(2000L)
        if (home.paginatorDropDown.displayed) {
            click(home.paginatorDropDown, "Paginator Drop Down value")
            waitFor { home.paginatorDropDownValue(value) }
            click(home.paginatorDropDownValue(value), "Paginator Drop Down value: " + value)
        }
    }
}
