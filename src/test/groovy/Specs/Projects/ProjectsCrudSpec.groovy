package Specs.Projects

import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.MyProfilePage
import Pages.Projects.ProjectsHomePage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import Utilities.Validations.VerifyUtil
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by E002149 on 6/20/2016.
 */
class ProjectsCrudSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME_1,PROJECT_NAME_2,PROJECT_NAME_3,PROJECT_NAME_4;
    VerifyUtil verifyUtil;
    public String currentMethod;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        verifyUtil = new VerifyUtil()
    }

    @Test(groups = "Projects", priority = 1, description = "Project Crud End to End Operations - Project Details, Edit Project, Upload Genomes")
    public void endToEndProjectCrudOperations(Method method){

        PROJECT_NAME_1 = PROJECT__NAME + generateRandom()
        PROJECT_NAME_2 = PROJECT__NAME + generateRandom()
        PROJECT_NAME_3 = PROJECT__NAME + generateRandom()
        PROJECT_NAME_4 = PROJECT__NAME + generateRandom()

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        clickOnNewProjectButton()
        createOrEditProject(PROJECT_NAME_1,PROJECT_DESCRIPTION_1, true, true, true, false,CREATE_ACTION)
        clickActionsAndOptionBasedOnProject(PROJECT_NAME_1,UPLOAD_GENOMES)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME_1, false, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickEmailOnHeaderPage()

        at MyProfilePage
        String profileName = getUserNameOnMyProfilePage()

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME_1, FOUR)
        clickOnTab(PUBLIC_PROJECTS)
        clickOnTab(WORKSPACE_PROJECTS)
        verifyUtil.verify(verifyProjectIsDisplayed(PROJECT_NAME_1),PROJECT_NAME_1+" is not displayed on Projects Home page")
        verifyUtil.verify(getProjectDescriptionBasedOnProjectName(PROJECT_NAME_1).equals(PROJECT_DESCRIPTION_1), "Description of Project "+PROJECT_NAME_1+" is not equal to "+ PROJECT_DESCRIPTION_1)
        verifyUtil.verify(getDateCreatedBasedOnProjectName(PROJECT_NAME_1).equals(getCurrentDate()), "Date Created of Project "+PROJECT_NAME_1+" is not equal to "+ getCurrentDate())
        verifyUtil.verify(getDateModifiedBasedOnProjectName(PROJECT_NAME_1).equals(getCurrentDate()), "Date Modified of Project "+PROJECT_NAME_1+" is not equal to "+ getCurrentDate())
        verifyUtil.verify(getCreatedByTextBasedOnProjectName(PROJECT_NAME_1).equals(profileName), "Created By of Project "+PROJECT_NAME_1+" is not equal to "+ profileName)

        clickActionsAndOptionBasedOnProject(PROJECT_NAME_1,EDIT_PROJECT_OPTION)
        createOrEditProject(EDIT_PROJECT_NAME,EDIT_PROJECT_DESCRIPTION, false, true, true, false,CANCEL)
        verifyUtil.verify(verifyProjectIsDisplayed(PROJECT_NAME_1),PROJECT_NAME_1+" is not displayed on Projects Home page")
        verifyUtil.verify(getProjectDescriptionBasedOnProjectName(PROJECT_NAME_1).equals(PROJECT_DESCRIPTION_1), "Description of Project "+PROJECT_NAME_1+" is not equal to "+ PROJECT_DESCRIPTION_1)

        clickActionsAndOptionBasedOnProject(PROJECT_NAME_1,EDIT_PROJECT_OPTION)
        createOrEditProject(EDIT_PROJECT_NAME,EDIT_PROJECT_DESCRIPTION, false, true, true, false,SAVE)
        verifyUtil.verify(verifyProjectIsDisplayed(EDIT_PROJECT_NAME),EDIT_PROJECT_NAME+" is not displayed on Projects Home page")
        verifyUtil.verify(getProjectDescriptionBasedOnProjectName(EDIT_PROJECT_NAME).equals(EDIT_PROJECT_DESCRIPTION), "Description of Project "+EDIT_PROJECT_NAME+" is not equal to "+ EDIT_PROJECT_DESCRIPTION)

        clickActionsAndOptionBasedOnProject(EDIT_PROJECT_NAME,PROJECT_DETAILS_OPTION)
        verifyUtil.verify(verifyProjectNameOnProjectDetailsPopUp().equals(EDIT_PROJECT_NAME),"Project Name: "+EDIT_PROJECT_NAME+" is not displayed on Project Details modal window")
        verifyUtil.verify(verifyProjectDescOnProjectDetailsPopUp().equals(EDIT_PROJECT_DESCRIPTION),"Project Description: "+EDIT_PROJECT_DESCRIPTION+" is not displayed on Project Details modal window")
        verifyUtil.verify(verifyProjectContributorsOnProjectDetailsPopUp().equals(profileName),"Project Contributors: "+profileName+" is not displayed on Project Details modal window")
        clickOnModalCloseIcon()

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME_2, true, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();
        fillUploadGenomeForm(PROJECT_NAME_3, true, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();
        fillUploadGenomeForm(PROJECT_NAME_4, true, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME_2, FOUR)
        refreshTillCountMatches(PROJECT_NAME_3, FOUR)
        refreshTillCountMatches(PROJECT_NAME_4, FOUR)
        projectSearch(INVALID_SEARCH_STRING)
        List resultsList = verifyProjectSearchResults(INVALID_SEARCH_STRING)
        resultsList.each {
            it-> verifyUtil.verify(it.contains("No results found."), "There are search results for invalid search string"+ INVALID_SEARCH_STRING)
        }
        projectSearch(VALID_SEARCH_STRING)
        List searchResultsList = verifyProjectSearchResults(VALID_SEARCH_STRING)
        searchResultsList.each {
            it-> verifyUtil.verify(it.contains(VALID_SEARCH_STRING), "Incorrect search results for valid search string"+ VALID_SEARCH_STRING)
        }

        verifyUtil.assertTestResult("Test Case '"+currentMethod+"' Assertions Failed :")
    }
}
