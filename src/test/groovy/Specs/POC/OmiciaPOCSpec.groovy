package Specs.POC

import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Upload_Genomes.UploadGenomePage
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by in02183 on 4/1/2016.
 */

class OmiciaPOCSpec extends BaseSpec{

    @Test(groups= "omicia_poc")
    public void testGenomeCount() {

        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES)

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName,true,true);
        waitForTheVCFFileToUpload();

        at HeaderPage
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openTab(PROJECTS)
        driver.get(driver.currentUrl)

        at ProjectsHomePage
        int actualGeneCount = getGenomeBasedOnProjectName(NewProjectName)
        clickProjectInProjectsHomePage(NewProjectName);

        at ProjectsPage
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
