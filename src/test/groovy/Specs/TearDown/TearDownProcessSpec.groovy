package Specs.TearDown

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
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/25/2016.
 */
@Test(groups = "tearDown")
class TearDownProcessSpec extends BaseSpec {

    @Test(priority = 1, description = "Deleting All the Test Clinical Reports. Need to delete clinical Reports before deleting anything else")
    public void deleteClinicalReports() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    id = getWorkspaceIdBasedOnName(workspace)
                    switchWorkspace(workspace)
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

    @Test(priority = 2, description = "This will delete all the Test Panels created during Automation")
    public void deletePanels() {
        String id;
        to LoginPage
        loginWithUser(NORMAL_USER);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)
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

    @Test(priority = 3, description = "This will Delete all the Test Gene Sets created during Automation")
    public void deleteAllGeneSets() {
        String id;
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)
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

    @Test(priority = 4, description = "This will delete all the Test Filtering protocols created during Automation")
    public void deleteFilteringProtocols() {
        String id;
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)
                        openTab(FILTERING_PROTOCOL);

                        at FilteringProtocolHomePage
                        deleteAllFilteringProtocols()

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

    @Test(priority = 5, description = "This will delete all the Test Projects created during Automation")
    public void deleteAllProjects() {
        String id;
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                try {
                    if (workspace.contains("Automation")) {
                        id = getWorkspaceIdBasedOnName(workspace)
                        switchWorkspace(workspace)
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
                    Assert.fail("Clinical Reporter Deletion Failed for WORKSPACE: " + workspace + " ,WORKSPACE_ID: " + id);
                    throwable.printStackTrace()
                }
        }
    }
}
