package Pages.Login

import Modules.Login.HeaderModule
import Modules.Login.HomeModule
import Utilities.Class.BasePage

/**
 * Created by in02183 on 4/1/2016.
 */
class OmiciaHomePage extends BasePage{

    static at = {header.homePageHeaderOmiciaText.displayed}

    static content = {
        home {module HomeModule}
        header {module HeaderModule}
    }

    def openTab(String tabName){
        click(home.homePageTab(tabName),tabName)
    }

    def openUploadGenomes(){
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

    def getNamesOfAllWorkSpaces() {
        waitFor { home.workSpaceDropDown }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        int numberOfWorkspaces = home.numberOfWorkSpaces.size();
        List workspaceNames = new ArrayList()
        for (int i = 0; i < numberOfWorkspaces; i++) {
            workspaceNames.add(home.numberOfWorkSpaces[i].text())
        }
        click(home.workSpaceDropDown, "Workspace Drop Down")
        return workspaceNames
    }
}
