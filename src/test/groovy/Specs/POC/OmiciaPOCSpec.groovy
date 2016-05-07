package Specs.POC

import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by in02183 on 4/1/2016.
 */

class OmiciaPOCSpec extends BaseSpec{

    SmokeTestData smokeData = new SmokeTestData();

    @Test(groups= "omicia_poc")
    public void testGenomeCount() {

        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES)

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName, true, true, smokeData.SHORT_FILE);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(NewProjectName, ONE)
        clickProjectInProjectsHomePage(NewProjectName);

        at ProjectsPage
        int expectedGeneCount = getNumberOfGenes();
        Assert.assertEquals(ONE, expectedGeneCount);
    }

    @Test(groups= "omicia_poc")
    public void uploadGemomeWithoutVCFFile(){
        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at OmiciaHomePage
        openUploadGenomes();

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName, false, false, NONE);
        waitFor {uploadGenome.vcfFileNotUploadedError}
    }
}
