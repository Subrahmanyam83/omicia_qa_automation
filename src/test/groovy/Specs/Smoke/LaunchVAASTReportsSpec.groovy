package Specs.Smoke

import Pages.App_Store.AppStoreAnalysisHomePage
import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Clinical_Reporter.VariantInterpretationHomePage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Projects.VariantReportPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import Utilities.Validations.VerifyUtil
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by E002183 on 5/9/2016.
 */
class LaunchVAASTReportsSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    VerifyUtil verifyUtil;
    public String currentMethod;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
        verifyUtil = new VerifyUtil()
    }

    @Test(groups = "smoke", priority = 1, description = "Launch VAAST Solo Analysis")
    public void launchVAASTSoloAnalysis(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)
        clickProjectInProjectsHomePage(PROJECT_NAME);

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        launchAppAndChooseValue(VAAST_SOLO_ANALYSIS)

        at AppStoreAnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(SOLO)
        page AppStoreAnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_SOLO_REPORT)

        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_SOLO_REPORT),"Report Heading is not equal to "+VAAST_SOLO_REPORT)

        page VariantInterpretationHomePage

        verifyUtil.verify(getNumberOfItems().equals(TWELVE),"Total number of items are not equal to "+TWELVE)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_SOLO_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_SOLO_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_SOLO_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_SOLO_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10, ONE).equals(data.POSITION_DBSNP_VALUE),"Position DBSNP of Variant: "+TTLL10+" is not equal to "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10, ONE).equals(data.CHANGE_VALUE),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10, ONE).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTRankBasedOnVariant(TTLL10, ONE).equals(TWO),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+TWO)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10, ONE).equals(data.VVP_CADD_VALUE),"VVPCADD of Variant: "+TTLL10+" is not equal to "+data.VVP_CADD_VALUE)
        verifyUtil.verify(getVAASTVScoreBasedOnVariant(TTLL10, ONE).equals(FOURTEEN_POINT_THREE_TWO),"VAASTV Score of Variant: "+TTLL10+" is not equal to "+FOURTEEN_POINT_THREE_TWO)
        verifyUtil.verify(getVAASTGScoreBasedOnVariant(TTLL10, ONE).equals(data.VAAST_G_SCORE_VALUE_RECESSIVE),"VAASTG Score of Variant: "+TTLL10+" is not equal to "+data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page VariantReportPage
        clickOnHeaderButton(DOMINANT)
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_SOLO_REPORT),"Report heading is not equal to "+VAAST_SOLO_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(FIVE),"Total number of Items are not equal to "+FIVE)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_SOLO_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_SOLO_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_SOLO_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_SOLO_REPORT_COLUMN_LIST + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10).equals(data.POSITION_DBSNP_VALUE),"Position DBSNP of Variant: "+TTLL10+" is not equal to "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.CHANGE_VALUE),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTRankBasedOnVariant(TTLL10).equals(THREE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10).equals(data.VVP_CADD_VALUE),"VVPCADD of Variant: "+TTLL10+" is not equal to "+data.VVP_CADD_VALUE)
        verifyUtil.verify(getVAASTVScoreBasedOnVariant(TTLL10).equals(FOURTEEN_POINT_THREE_TWO),"VAASTV Score of Variant: "+TTLL10+" is not equal to "+FOURTEEN_POINT_THREE_TWO)
        verifyUtil.verify(getVAASTGScoreBasedOnVariant(TTLL10).equals(data.VAAST_G_SCORE_VALUE_DOMINANT),"VAASTG Score of Variant: "+TTLL10+" is not equal to "+data.VAAST_G_SCORE_VALUE_DOMINANT)

        page VariantReportPage
        clickOnHeaderButton(VAAST_VIEWER)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("/viewer"))
            verifyContentOnVAASTViewerPage()
            driver.close()
        }
        Assert.assertEquals(getResponseCodeForExportReportRequest(), 200);
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(VAAST_SOLO_REPORT)
        Assert.equals(getNumberOfReports() == (reportSize-1))
    }

    @Test(groups = "smoke", priority = 2, description = "Launch VAAST Trio Analysis")
    public void launchVAASTTrioAnalysis(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)
        clickProjectInProjectsHomePage(PROJECT_NAME);

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        launchAppAndChooseValue(VAAST_TRIO_ANALYSIS)

        at AppStoreAnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(TRIO)
        page AppStoreAnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_TRIO_REPORT)

        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_TRIO_REPORT),"Report Heading is not equal to "+VAAST_TRIO_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(TWO),"Total number of items are not equal to "+TWO)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_TRIO_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_TRIO_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_TRIO_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_TRIO_REPORT_COLUMN_LIST + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10, ONE).equals(data.POSITION_DBSNP_VALUE),"Position DBSNP of Variant: "+TTLL10+" is not equal to "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10, ONE).equals(data.CHANGE_VALUE),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10, ONE).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTRankBasedOnVariant(TTLL10, ONE).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10, ONE).equals(data.VVP_CADD_VALUE),"VVPCADD of Variant: "+TTLL10+" is not equal to "+data.VVP_CADD_VALUE)
        verifyUtil.verify(getVAASTVScoreBasedOnVariant(TTLL10, ONE).equals(FOURTEEN_POINT_THREE_TWO),"VAASTV Score of Variant: "+TTLL10+" is not equal to "+FOURTEEN_POINT_THREE_TWO)
        verifyUtil.verify(getVAASTGScoreBasedOnVariant(TTLL10, ONE).equals(data.VAAST_G_SCORE_VALUE_RECESSIVE),"VAASTG Score of Variant: "+TTLL10+" is not equal to "+data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_TRIO_REPORT),"Report Heading name is not equal to "+VAAST_TRIO_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(ZERO),"Total number of items are not equal to "+ZERO)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_TRIO_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_TRIO_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_TRIO_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_TRIO_REPORT_COLUMN_LIST + "Actual: "+ getDefaultColumnNamesOnPage())
        }

        page VariantReportPage
        clickOnHeaderButton(VAAST_VIEWER)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("/viewer"))
            verifyContentOnVAASTViewerPage()
            driver.close()
        }
        Assert.assertEquals(getResponseCodeForExportReportRequest(), 200);
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(VAAST_TRIO_REPORT)
        Assert.equals(getNumberOfReports() == (reportSize-1))
    }

    @Test(groups = "smoke", priority = 3, description = "Launch VAAST Quad Analysis")
    public void launchVAASTQuadAnalysis(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)
        clickProjectInProjectsHomePage(PROJECT_NAME);

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        launchAppAndChooseValue(VAAST_QUAD_ANALYSIS)

        at AppStoreAnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(QUAD)
        page AppStoreAnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_QUAD_REPORT)

        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_QUAD_REPORT),"Report Heading name is not equal to "+VAAST_QUAD_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(TWO),"Total number of items are not equal to "+TWO)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_QUAD_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_QUAD_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_QUAD_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_QUAD_REPORT_COLUMN_LIST + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10, ONE).equals(data.POSITION_DBSNP_VALUE),"Position DBSNP of Variant: "+TTLL10+" is not equal to "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10, ONE).equals(data.CHANGE_VALUE),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10, ONE).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTRankBasedOnVariant(TTLL10, ONE).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10, ONE).equals(data.VVP_CADD_VALUE),"VVPCADD of Variant: "+TTLL10+" is not equal to "+data.VVP_CADD_VALUE)
        verifyUtil.verify(getVAASTVScoreBasedOnVariant(TTLL10, ONE).equals(FOURTEEN_POINT_THREE_TWO),"VAASTV Score of Variant: "+TTLL10+" is not equal to "+FOURTEEN_POINT_THREE_TWO)
        verifyUtil.verify(getVAASTGScoreBasedOnVariant(TTLL10, ONE).equals(data.VAAST_G_SCORE_VALUE_RECESSIVE),"VAASTG Score of Variant: "+TTLL10+" is not equal to "+data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(VAAST_QUAD_REPORT),"Report heading is not equal to "+VAAST_QUAD_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(ZERO),"Total number of items are not equal to "+ ZERO)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_QUAD_REPORT_COLUMN_LIST_GEL),"Variant Interpretation column names are not matching. Expected: "+ VAAST_QUAD_REPORT_COLUMN_LIST_GEL + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(VAAST_QUAD_REPORT_COLUMN_LIST),"Variant Interpretation column names are not matching. Expected: "+ VAAST_QUAD_REPORT_COLUMN_LIST + "Actual: "+ getDefaultColumnNamesOnPage())
        }

        page VariantReportPage
        clickOnHeaderButton(VAAST_VIEWER)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("/viewer"))
            verifyContentOnVAASTViewerPage()
            driver.close()
        }
        Assert.assertEquals(getResponseCodeForExportReportRequest(), 200);
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(VAAST_QUAD_REPORT)
        Assert.equals(getNumberOfReports() == (reportSize-1))
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodExecution(){
        verifyUtil.assertTestResult("Test Case '"+currentMethod+"' Assertions Failed :")
    }
}
