package Pages.Login

import Modules.Login.HeaderModule
import Modules.Login.HomeModule
import Modules.Login.LoginModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/5/2016.
 */
class HeaderPage extends BasePage{

    static at = {header.homePageHeaderOmiciaText.displayed}

    static content = {
        header {module HeaderModule}
        login  {module LoginModule}
        home {module HomeModule}
    }

    def clickOnMenuAndSelectOption(String pageName){
        waitFor {header.menuDropDown}
        click(header.menuDropDown,"Menu Drop Down");
        click(header.menuDropDownValue(pageName), "Menu Drop Down Value")
    }

    def signOut(){
        click(header.signOut,"Header Sign Out Button");
        waitFor {login.signInLink}
    }

    def goToHomePage() {
        waitFor { header.omiciaOpalHomePage }
        click(header.omiciaOpalHomePage, "HomePage Button on Header")
    }

    def clickOnOPALAdminAndChooseTab(String tabName) {
        waitFor { header.opalAdminButton }
        click(header.opalAdminButton, "OPAL ADMIN Button")
        waitFor { header.opalAdminTabs(tabName) }
        click(header.opalAdminTabs(tabName), "Opal Admin Tab: " + tabName)
    }

    def switchWorkspace(String workspace) {
        waitFor { home.workSpaceDropDown }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        waitFor { home.workSpaceDropDownValue(workspace) }
        click(home.workSpaceDropDownValue(workspace), "Workspace Value on Drop Down")
        waitFor { home.switchWorkspaceAlert("Selected workspace: " + workspace) }
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

    def getNamesOfAllWorkSpaces() {
        int numberOfWorkspaces = home.numberOfWorkSpacesRows.size();
        List workspaceNames = new ArrayList()
        for (int i = 0; i < numberOfWorkspaces; i++) {
            workspaceNames.add(home.numberOfWorkSpacesRows[i].text())
        }
        return workspaceNames
    }

    def clickOnWorkspaceDropDown(){
        waitFor {home.workSpaceDropDown}
        click(home.workSpaceDropDown, "Workspace Drop Down")
    }

    def getWorkspaceIdBasedOnName(String workSpaceName) {
        String workspaceId = home.workspaceIdBasedOnWorkspaceName(workSpaceName)
        return workspaceId
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
