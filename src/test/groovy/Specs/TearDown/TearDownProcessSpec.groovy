package Specs.TearDown

import Pages.Admin.ManageWorkspacePage
import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Filtering_Protocol.FilteringProtocolHomePage
import Pages.Gene_Sets.GeneSetsPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/25/2016.
 */
@Test(groups = "tearDown")
class TearDownProcessSpec extends BaseSpec {

    @Test(priority = 1, description = "Delete all Automation Clinical Reports created during Automation")
    public void deleteClinicalReports() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    id = getWorkspaceIdBasedOnName(workspace)
                    switchWorkspace(workspace)

                    at HeaderPage
                    goToHomePage()

                    at OmiciaHomePage
                    openTab(CLINICAL_REPORTER);

                    at ClinicalReporterPage
                    deleteAllClinicalReports()

                    at HeaderPage
                    goToHomePage()

                    at OmiciaHomePage
                }
                catch (Throwable throwable) {
                    Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + " ,WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 2, description = "Delete all the Test Panels created during Automation")
    public void deletePanels() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                        openTab(PANEL_BUILDER);

                        at PanelBuilderPage
                        deleteAllPanels()

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                    }
                }
                catch (Throwable throwable) {
                    Assert.fail("Panel Deletion Failed for WORKSPACE: " + workspace + " ,WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 3, description = "Delete all the Test Gene Sets created during Automation")
    public void deleteAllGeneSets() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                        openTab(GENE_SETS);

                        at GeneSetsPage
                        deleteSets(MY_SET);

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                    }
                }
                catch (Throwable throwable) {
                    Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + " ,WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 4, description = "Delete all the Test Filtering protocols created during Automation")
    public void deleteFilteringProtocols() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                        openTab(FILTERING_PROTOCOL);

                        at FilteringProtocolHomePage
                        deleteAllFilteringProtocols()

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                    }
                }
                catch (Throwable throwable) {
                    Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + " , WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 5, description = "Delete all the Test Projects created during Automation")
    public void deleteAllProjects() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)

                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                        openTab(PROJECTS);

                        at ProjectsHomePage
                        def projectNames = getAllProjectNames();

                        if (!projectNames.size().equals(ZERO)) {
                            for (String projectName in projectNames) {
                                clickProjectInProjectsHomePage(projectName);
                                at ProjectsPage
                                deleteProjects();
                                at ProjectsHomePage
                            }
                        }
                        at HeaderPage
                        goToHomePage()

                        at OmiciaHomePage
                    }
                }
                catch (Throwable throwable) {
                    Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + ", WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }

    @Test(priority = 6, description = "Remove test user from the Automation Workspaces")
    public void deleteUsersFromWorkspaces() {
        String id;
        List workSpaceList = new LinkedList();
        to LoginPage
        loginWithUser(NORMAL_USER);

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                    if (workspace.contains("ACMG_Automation_Workspace")) {
                        try{
                            id = getWorkspaceIdBasedOnName(workspace)
                            workSpaceList.add(id)
                        }
                        catch(Throwable throwable){
                            Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + ", WORKSPACE_ID: " + id);
                            throwable.printStackTrace()
                        }
                    }
                }

        if(!workSpaceList.size().equals(ZERO)){
            at HeaderPage
            signOut()

            to LoginPage
            loginWithUser(ADMIN)

            workSpaceList.each {
                wid->
                    go(System.getProperty("geb.build.baseUrl")+"admin_tools/view_workspace?workspace_id="+wid)
                    page ManageWorkspacePage
                    goToTab(MEMBERS)
                    deleteAllMembersFromMembersTab()
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    def signOutOfOPAL() {
        at HeaderPage
        signOut()
    }
}
