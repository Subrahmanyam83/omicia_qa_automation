package Specs.OMICIA

import Pages.OMICIA.HeaderPage
import Pages.OMICIA.OmiciaHomePage
import Pages.OMICIA.LoginPage
import Pages.OMICIA.ProjectsHomePage
import Pages.OMICIA.ProjectsPage
import Pages.OMICIA.UploadGenomePage
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by in02183 on 4/1/2016.
 */

class OmiciaPOC extends BaseSpec{

    @Test(groups= "omicia_poc")
    public void testGenomeCount() {

        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at OmiciaHomePage
        openUploadGenomes();

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName,true,true);
        waitForTheVCFFileToUpload();

        at HeaderPage
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openProjects()
        driver.get(driver.currentUrl)

        at ProjectsHomePage
        int actualGeneCount = getGenomeBasedOnProjectName(NewProjectName)
        clickProjectInProjectsHomePage(NewProjectName);
        int expectedGeneCount = getNumberOfGenomes();
        Assert.assertEquals(actualGeneCount,expectedGeneCount);

        at HeaderPage
        clickOnMenuAndSelectOption("Projects")

        at ProjectsHomePage
        clickProjectInProjectsHomePage(NewProjectName);

        at ProjectsPage
        deleteProject();

        at ProjectsHomePage
    }

    @Test(groups= "omicia_poc")
    public void uploadGemomeWithoutVCFFile(){
        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at OmiciaHomePage
        openUploadGenomes();

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName,false,false);
        waitFor {uploadGenome.vcfFileNotUploadedError}
    }
}
