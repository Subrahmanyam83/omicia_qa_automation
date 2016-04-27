package Specs.Smoke

import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Filtering_Protocol.FilteringProtocolHomePage
import Pages.Gene_Sets.GeneSetsPage
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
class TearDownProcessSpec extends BaseSpec{

    @Test(description = "This will delete all the Test Projects created during Automation")
    public void deleteAllProjects() {

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(PROJECTS);

        at ProjectsHomePage
        def projectNames = getAllProjectNames();

        if (!projectNames.size().equals(ZERO)) {
            for (String projectName in projectNames) {
                clickProjectInProjectsHomePage(projectName);
                at ProjectsPage
                deleteProject();
                at ProjectsHomePage
            }
        }
    }

    @Test(description = "This will Delete all the Test Gene Sets created during Automation")
    public void deleteAllGeneSets() {
        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(GENE_SETS);

        at GeneSetsPage
        deleteSets(MY_SET);
    }

    @Test(description = "This will delete all the Test Panels created during Automation")
    public void deletePanels() {
        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(PANEL_BUILDER);

        at PanelBuilderPage
        deleteAllPanels()
    }

    @Test(description = "Deleting All the Test Clinical Reports. Need to delete clinical Reports before deleting anything else", priority = 1)
    public void deleteClinicalReports() {
        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(CLINICAL_REPORTER);

        at ClinicalReporterPage
        deleteAllClinicalReports()
    }

    @Test(description = "This will delete all the Test Filtering protocols created during Automation")
    public void deleteFilteringProtocols() {
        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(FILTERING_PROTOCOL);

        at FilteringProtocolHomePage
        deleteAllFilteringProtocols()
    }
}
