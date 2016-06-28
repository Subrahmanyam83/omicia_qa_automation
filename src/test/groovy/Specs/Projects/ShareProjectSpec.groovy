package Specs.Projects

import Pages.Admin.ManageWorkspacePage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Projects.ProjectsHomePage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import Utilities.Validations.VerifyUtil
import org.openqa.selenium.Keys
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by E002149 on 6/23/2016.
 */
class ShareProjectSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    VerifyUtil verifyUtil;
    public String currentMethod;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
        verifyUtil = new VerifyUtil()

        to LoginPage
        loginWithUser(ADMIN);

        go(System.getProperty("geb.build.baseUrl") + "admin_tools/view_workspace?workspace_id=" + WID)
        page ManageWorkspacePage
        goToTab(MEMBERS)
        boolean userExist = checkIfMemberIsDisplayed(USERLOGINEMAIL)
        if (!userExist) {
            addMemberToWorkspace(USERLOGINEMAIL)
            verifyUtil.verify(checkIfMemberIsDisplayed(USERLOGINEMAIL), "New Member " + STRING_USERNAME + " was not added successfully")
        }

        at HeaderPage
        signOut()
    }

    @Test(groups = "Projects", priority = 1, description = "Share Project flow as Viewer ")
    public void shareProjectAsViewer(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        clickOnNewProjectButton()
        createOrEditProject(PROJECT_NAME, PROJECT_DESCRIPTION_1, false, true, true, false, CREATE_ACTION)
        clickActionsAndOptionBasedOnProject(PROJECT_NAME, UPLOAD_GENOMES)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, false, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)
        clickActionsAndOptionBasedOnProject(PROJECT_NAME, SHARE_PROJECT_OPTION)
        shareProjectWithUser(USERNAMES, PROJECTROLE_VIEWER, SAVE_CHANGES)

        withNewWindow({openNewPrivateWindow()}, wait: true) {
            to LoginPage
            loginWithUser(OWNER);

            at HeaderPage
            switchWorkspace(PRE_ACMG_WORKSPACE)
            clickOnMenuAndSelectOption(PROJECTS)

            at ProjectsHomePage
            verifyUtil.verify(verifyProjectIsDisplayed(PROJECT_NAME), PROJECT_NAME + " is not displayed on Projects Home page")
            List actionsList = getActionsListBasedOnProject(PROJECT_NAME)
            verifyUtil.verify(actionsList.equals(ACTIONSLIST_VIEWER), "List of Actions are not matching for the Project " + PROJECT_NAME + " having the role as " + PROJECTROLE_VIEWER + " Expected: " + ACTIONSLIST_VIEWER + "Actual: " + actionsList)
            clickActionsAndOptionBasedOnProject(PROJECT_NAME, REMOVEME_OPTION)
            removeAccessFlow(REMOVEPROJECT_BUTTON)
            verifyUtil.verify(verifyProjectIsNotDisplayed(PROJECT_NAME), PROJECT_NAME + " is displayed on Projects Home page after removal")
        }

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        clickActionsAndOptionBasedOnProject(PROJECT_NAME, SHARE_PROJECT_OPTION)
        verifyUtil.verify(checkIfUserIsNotDisplayedOnShareProjectModal(STRING_USERNAME), "User " + STRING_USERNAME + " is displayed on Share Project Modal")
        clickOnModalCloseIcon()

        at HeaderPage
        signOut()

        verifyUtil.assertTestResult("Test Case '" + currentMethod + "' Assertions Failed :")
    }


    @Test(groups = "Projects", priority = 2, description = "Share Project flow as Contributor")
    public void shareProjectAsContributor(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        clickOnNewProjectButton()
        createOrEditProject(PROJECT_NAME, PROJECT_DESCRIPTION_1, false, true, true, false, CREATE_ACTION)
        clickActionsAndOptionBasedOnProject(PROJECT_NAME, UPLOAD_GENOMES)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, false, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)
        clickActionsAndOptionBasedOnProject(PROJECT_NAME, SHARE_PROJECT_OPTION)
        shareProjectWithUser(USERNAMES, PROJECTROLE_CONTRIBUTOR, SAVE_CHANGES)

        withNewWindow({openNewPrivateWindow()}, wait: true) {
            to LoginPage
            loginWithUser(OWNER);

            at HeaderPage
            switchWorkspace(PRE_ACMG_WORKSPACE)
            clickOnMenuAndSelectOption(PROJECTS)

            at ProjectsHomePage
            verifyUtil.verify(verifyProjectIsDisplayed(PROJECT_NAME), PROJECT_NAME + " is not displayed on Projects Home page")
            List actionsList = getActionsListBasedOnProject(PROJECT_NAME)
            verifyUtil.verify(actionsList.equals(ACTIONSLIST_CONTRIBUTOR), "List of Actions are not matching for the Project " + PROJECT_NAME + " having the role as " + PROJECTROLE_CONTRIBUTOR + " Expected: " + ACTIONSLIST_CONTRIBUTOR + "Actual: " + actionsList)
            clickActionsAndOptionBasedOnProject(PROJECT_NAME, EDIT_PROJECT_OPTION)
            createOrEditProject(EDIT_PROJECT_NAME, EDIT_PROJECT_DESCRIPTION, false, true, true, false, SAVE)
            verifyUtil.verify(verifyProjectIsDisplayed(EDIT_PROJECT_NAME), EDIT_PROJECT_NAME + " is not displayed on Projects Home page")
            verifyUtil.verify(getProjectDescriptionBasedOnProjectName(EDIT_PROJECT_NAME).equals(EDIT_PROJECT_DESCRIPTION), "Description of Project " + EDIT_PROJECT_NAME + " is not equal to " + EDIT_PROJECT_DESCRIPTION)
        }

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        verifyUtil.verify(verifyProjectIsDisplayed(EDIT_PROJECT_NAME), EDIT_PROJECT_NAME + " is not displayed on Projects Home page")
        verifyUtil.verify(getProjectDescriptionBasedOnProjectName(EDIT_PROJECT_NAME).equals(EDIT_PROJECT_DESCRIPTION), "Description of Project " + EDIT_PROJECT_NAME + " is not equal to " + EDIT_PROJECT_DESCRIPTION)

        at HeaderPage
        signOut()

        verifyUtil.assertTestResult("Test Case '" + currentMethod + "' Assertions Failed :")
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodExecution() {

        to LoginPage
        loginWithUser(ADMIN);

        go(System.getProperty("geb.build.baseUrl") + "admin_tools/view_workspace?workspace_id=" + WID)
        page ManageWorkspacePage
        goToTab(MEMBERS)
        boolean userExist = checkIfMemberIsDisplayed(USERLOGINEMAIL)
        if (userExist) {
            deleteMembersFromWorkspace(USERLOGINEMAIL)
            verifyUtil.verify(checkIfMemberIsNotDisplayed(USERLOGINEMAIL), "Member " + STRING_USERNAME + " is displayed on Manage Workspace page")
        }
    }

}
