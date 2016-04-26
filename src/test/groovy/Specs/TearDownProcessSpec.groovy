package Specs

import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Utilities.Class.BaseSpec
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/25/2016.
 */
class TearDownProcessSpec extends BaseSpec{

    @Test
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

    @Test
    public deleteAllGeneSets(){
        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(GENE_SETS);

        clickOnSetType(MY_SET);




    }
}
