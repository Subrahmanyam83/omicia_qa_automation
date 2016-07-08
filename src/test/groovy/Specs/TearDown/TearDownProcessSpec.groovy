package Specs.TearDown

import Pages.Admin.ManageWorkspacePage
import Pages.Filtering_Protocol.FilteringProtocolHomePage
import Pages.Gene_Sets.GeneSetsPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/25/2016.
 */
@Test(groups = "tearDown")
class TearDownProcessSpec extends BaseSpec {

    public List workSpaceIdList = new LinkedList();
    public List workSpaceNamesList = new LinkedList();
    public Map wid_wName_Map = new HashMap();

    @Test(priority = 1,description = "Delete all Automation Clinical Reports created during Automation)")
    public void deleteClinicalReports() {
        String id;

        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        clickOnWorkspaceDropDown()
        getNamesOfAllWorkSpaces().each {
            workspace ->
                if (workspace.contains("ACMG_Automation_Workspace")) {
                    id = getWorkspaceIdBasedOnName(workspace)
                    workSpaceIdList.add(id);
                    workSpaceNamesList.add(workspace);
                    wid_wName_Map.put(id,workspace);
                }
        }
        clickOnWorkspaceDropDown()

        if (!workSpaceIdList.size().equals(ZERO)) {
            at HeaderPage
            signOut()

            to LoginPage
            loginWithUser(ADMIN)

            workSpaceIdList.each {
                wid ->
                    getEreportTest().log(INFO,"NAVIGATING TO WID: "+wid);
                        go(System.getProperty("geb.build.baseUrl") + "admin_tools/view_workspace?workspace_id=" + wid)
                        page ManageWorkspacePage
                        goToTab(GROUPS)
                        clickOnCheckBoxUnderGroupsTab([CLINICAL_REPORTER_ACCESS])
                        goToTab(CLINICAL_REPORTS)
                        deleteAllClinicalReports()
            }
        }
    }

    @Test(priority = 2, description = "Delete all the Test Panels created during Automation", dependsOnMethods = "deleteClinicalReports")
    public void deletePanels() {
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        workSpaceNamesList.each {
            workspace ->
                try {
                    switchWorkspace(workspace)
                    clickOnMenuAndSelectOption(PANEL_BUILDER)

                    at PanelBuilderPage
                    deleteAllPanels()

                    at HeaderPage
                    }
                catch (Throwable throwable) {
                    Assert.fail("Panel Deletion Failed for WORKSPACE: "+workspace);
                    throwable.printStackTrace()
            }
        }
    }

    @Test(priority = 3, description = "Delete all the Test Gene Sets created during Automation", dependsOnMethods = "deleteClinicalReports")
    public void deleteAllGeneSets() {
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        workSpaceNamesList.each {
            workspace ->
                try {
                    switchWorkspace(workspace)
                    clickOnMenuAndSelectOption(GENE_SETS)

                    at GeneSetsPage
                    deleteSets(MY_SET);

                    at HeaderPage
                    }
                catch (Throwable throwable) {
                    Assert.fail("Gene Sets Deletion Failed for WORKSPACE: "+workspace );
                    throwable.printStackTrace()
            }
        }
    }

    @Test(priority = 4, description = "Delete all the Test Filtering protocols created during Automation", dependsOnMethods = "deleteClinicalReports")
    public void deleteFilteringProtocols() {
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        workSpaceNamesList.each {
            workspace ->
                try {
                    switchWorkspace(workspace)
                    clickOnMenuAndSelectOption(FILTERING_PROTOCOL)

                    at FilteringProtocolHomePage
                    deleteAllFilteringProtocols()

                    at HeaderPage
                }
                catch (Throwable throwable) {
                    Assert.fail("Filtering Protocol Deletion Failed for WORKSPACE: "+workspace);
                    throwable.printStackTrace()
            }
        }
    }

    @Test(priority = 5, description = "Delete all the Test Projects created during Automation", dependsOnMethods = "deleteClinicalReports")
    public void deleteAllProjects() {
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        workSpaceNamesList.each {
            workspace ->
                try {
                    switchWorkspace(workspace)
                    clickOnMenuAndSelectOption(PROJECTS)

                    at ProjectsHomePage
                    while(!getNumberOfProjects().equals(0)){
                        clickOnProjectFolderIcon()
                        at ProjectsPage
                        deleteProjects();
                        at ProjectsHomePage
                    }
                    at HeaderPage
                }
                catch (Throwable throwable) {
                    Assert.fail("Project Deletion Failed for WORKSPACE: " + workspace);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 6, description = "Remove test user from the Automation Workspaces", dependsOnMethods = ["deleteClinicalReports","deletePanels","deleteAllGeneSets","deleteFilteringProtocols","deleteAllProjects"])
    public void deleteUsersFromWorkspaces() {
        to LoginPage
        loginWithUser(ADMIN)

        workSpaceIdList.each {
            wid ->
                if (!wid_wName_Map.get(wid).equals("Pre_ACMG_Automation_Workspace_Omicia")) {
                    try {
                        go(System.getProperty("geb.build.baseUrl") + "/admin_tools/view_workspace?workspace_id=" + wid)
                        page ManageWorkspacePage
                        goToTab(MEMBERS)
                        deleteAllMembersFromMembersTab()
                    }
                    catch (Throwable throwable) {
                        Assert.fail("Removal of Users from the Workspace Failed for WORKSPACE_ID: " + wid);
                        throwable.printStackTrace()
                    }
                }
        }
    }
}
