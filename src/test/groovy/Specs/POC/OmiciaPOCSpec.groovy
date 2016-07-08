package Specs.POC

import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Utilities.Data.SmokeTestData
import Utilities.Class.BaseSpec
import Utilities.Validations.VerifyUtil
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by in02183 on 4/1/2016.
 */

class OmiciaPOCSpec extends BaseSpec{

    SmokeTestData smokeData = new SmokeTestData();

    @Test(groups = "omicia_poc", description = "Verify Genome Count in Project after VCF Upload")
    public void testGenomeCount(Method method) {

        String NewProjectName = "Test-OMICIA-Project-"+Math.random();
        def verifyUtil = new VerifyUtil()

        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

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
        verifyUtil.verify(ONE.equals(expectedGeneCount), "Genome Count validation Failed")
        verifyUtil.assertTestResult("Test Case '"+method.name+"' Assertions Failed :")
    }

    @Test(groups = "omicia_poc", description = "Upload Genome without VCF File")
    public void uploadGemomeWithoutVCFFile(){
        String NewProjectName = "Test-OMICIA-Project-"+Math.random();

        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

        at UploadGenomePage
        fillUploadGenomeForm(NewProjectName, false, false, NONE);
        waitFor {uploadGenome.vcfFileNotUploadedError}
    }
}
