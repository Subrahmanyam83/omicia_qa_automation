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
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/25/2016.
 */
@Test(groups = "tearDown")
class TearDownProcessSpec extends BaseSpec{

    @Test(priority = 1, description = "Deleting All the Test Clinical Reports. Need to delete clinical Reports before deleting anything else")
    public void deleteClinicalReports() {
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                switchWorkspace(workspace)
                openTab(CLINICAL_REPORTER);

                at ClinicalReporterPage
                deleteAllClinicalReports()

                at HeaderPage
                goToHomePage()

                at OmiciaHomePage
        }
    }

    @Test(priority = 2, description = "This will delete all the Test Panels created during Automation")
    public void deletePanels() {
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                switchWorkspace(workspace)
                openTab(PANEL_BUILDER);

                at PanelBuilderPage
                deleteAllPanels()

                at HeaderPage
                goToHomePage()

                at OmiciaHomePage
        }
    }

    @Test(priority = 3, description = "This will Delete all the Test Gene Sets created during Automation")
    public void deleteAllGeneSets() {
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                switchWorkspace(workspace)
                openTab(GENE_SETS);

                at GeneSetsPage
                deleteSets(MY_SET);

                at HeaderPage
                goToHomePage()

                at OmiciaHomePage
        }
    }

    @Test(priority = 4, description = "This will delete all the Test Filtering protocols created during Automation")
    public void deleteFilteringProtocols() {
        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
                switchWorkspace(workspace)
                openTab(FILTERING_PROTOCOL);

                at FilteringProtocolHomePage
                deleteAllFilteringProtocols()

                at HeaderPage
                goToHomePage()

                at OmiciaHomePage
        }
    }

    @Test(priority = 5, description = "This will delete all the Test Projects created during Automation")
    public void deleteAllProjects() {

        to LoginPage
        loginWithUser(ADMIN);

        at OmiciaHomePage
        getNamesOfAllWorkSpaces().each {
            workspace ->
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
}
