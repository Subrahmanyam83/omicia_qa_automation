package Specs.Smoke

import Pages.App_Store.AnalysisHomePage
import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Clinical_Reporter.VariantInterpretationHomePage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Projects.ReportsHomePage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 5/9/2016.
 */
class LaunchVAASTReportsSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;

    @BeforeMethod
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
    }

    @Test(groups = "smoke", priority = 1)
    public void launchVAASTSoloAnalysis() {

        to LoginPage
        signIns();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

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

        at AnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(SOLO)
        page AnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_SOLO_REPORT)

        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_SOLO_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), TWELVE)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10, ONE), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10, ONE), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10, ONE), MISSENSE)
        Assert.assertEquals(getVAASTRankBasedOnVariant(TTLL10, ONE), TWO)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10, ONE), data.VVP_CADD_VALUE)
        Assert.assertEquals(getVAASTVScoreBasedOnVariant(TTLL10, ONE), FOURTEEN_POINT_THREE_TWO)
        Assert.assertEquals(getVAASTGScoreBasedOnVariant(TTLL10, ONE), data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page ReportsHomePage
        clickOnHeaderButton(DOMINANT)
        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_SOLO_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), FIVE)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getVAASTRankBasedOnVariant(TTLL10), THREE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10), data.VVP_CADD_VALUE)
        Assert.assertEquals(getVAASTVScoreBasedOnVariant(TTLL10), FOURTEEN_POINT_THREE_TWO)
        Assert.assertEquals(getVAASTGScoreBasedOnVariant(TTLL10), data.VAAST_G_SCORE_VALUE_DOMINANT)
    }

    @Test(groups = "smoke", priority = 2)
    public void launchVAASTTrioAnalysis() {

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

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

        at AnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(TRIO)
        page AnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_TRIO_REPORT)

        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_TRIO_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), TWO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_TRIO_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10, ONE), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10, ONE), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10, ONE), MISSENSE)
        Assert.assertEquals(getVAASTRankBasedOnVariant(TTLL10, ONE), ONE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10, ONE), data.VVP_CADD_VALUE)
        Assert.assertEquals(getVAASTVScoreBasedOnVariant(TTLL10, ONE), FOURTEEN_POINT_THREE_TWO)
        Assert.assertEquals(getVAASTGScoreBasedOnVariant(TTLL10, ONE), data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page ReportsHomePage
        clickOnHeaderButton(DE_NOVO)
        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_TRIO_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), ZERO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_TRIO_REPORT_COLUMN_LIST)
        }
    }

    @Test(groups = "smoke", priority = 3)
    public void launchVAASTQuadAnalysis() {

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

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

        at AnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(QUAD)
        page AnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(VAAST_QUAD_REPORT)

        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_QUAD_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), TWO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_QUAD_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10, ONE), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10, ONE), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10, ONE), MISSENSE)
        Assert.assertEquals(getVAASTRankBasedOnVariant(TTLL10, ONE), ONE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10, ONE), data.VVP_CADD_VALUE)
        Assert.assertEquals(getVAASTVScoreBasedOnVariant(TTLL10, ONE), FOURTEEN_POINT_THREE_TWO)
        Assert.assertEquals(getVAASTGScoreBasedOnVariant(TTLL10, ONE), data.VAAST_G_SCORE_VALUE_RECESSIVE)

        page ReportsHomePage
        clickOnHeaderButton(DE_NOVO)
        at ReportsHomePage
        waitForThePageToLaunch()
        Assert.assertEquals(verifyReportHeading(), VAAST_QUAD_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), ZERO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_QUAD_REPORT_COLUMN_LIST)
        }

    }
}
