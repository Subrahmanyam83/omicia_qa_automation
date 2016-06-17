package Specs.Smoke

import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Clinical_Reporter.VariantInterpretationHomePage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Projects.VariantReportPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 5/9/2016.
 */
class LaunchFlexReportsSpec extends BaseSpec{

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
    }

    @Test(groups = "smoke",description = "Launch FLEX Trio Reports")
    public void launchFlexTrioReports() {

        to LoginPage
        signIn();

        at HeaderPage
        goToHomePage()

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
        launchAppAndChooseValue(FLEX_TRIO_ANALYSIS)

        at AppStoreAnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(TRIO)
        page AppStoreAnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(FLEX_TRIO_REPORT)

        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        Assert.assertEquals(getReportHeading(), FLEX_TRIO_REPORT)

        page VariantInterpretationHomePage

        Assert.assertEquals(getNumberOfItems(), THREE)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_TRIO_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10, ONE), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10, ONE), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10, ONE), MISSENSE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10, ONE), data.VVP_CADD_VALUE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        waitForTheReportToAppearWithNoOpacity()
        Assert.assertEquals(getReportHeading(), FLEX_TRIO_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), ZERO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_TRIO_REPORT_COLUMN_LIST)
        }
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(FLEX_TRIO_REPORT)
        Assert.equals(getNumberOfReports() == (reportSize-1))
    }

    @Test(groups = "smoke",description = "Launch FLEX Quad Reports")
    public void launchFlexQuadReports() {

        to LoginPage
        signIn();

        at HeaderPage
        goToHomePage()

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
        launchAppAndChooseValue(FLEX_QUAD_ANALYSIS)

        at AppStoreAnalysisHomePage
        page ClinicalReporterPage
        chooseGeneForEachMember(QUAD)
        page AppStoreAnalysisHomePage
        clickOnRun()

        at ProjectsPage
        waitTillAllVariantReportsAreAvailable()
        clickOnReport(FLEX_QUAD_REPORT)

        at VariantReportPage
        waitForTheReportToAppearWithNoOpacity()
        Assert.assertEquals(getReportHeading(), FLEX_QUAD_REPORT)

        page VariantInterpretationHomePage

        Assert.assertEquals(getNumberOfItems(), THREE)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_QUAD_REPORT_COLUMN_LIST)
        }
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10, ONE), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10, ONE), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10, ONE), MISSENSE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10, ONE), data.VVP_CADD_VALUE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        waitForTheReportToAppearWithNoOpacity()
        Assert.assertEquals(getReportHeading(), FLEX_QUAD_REPORT)
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), ZERO)
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), FLEX_QUAD_REPORT_COLUMN_LIST)
        }
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(FLEX_QUAD_REPORT)
        Assert.equals(getNumberOfReports() == (reportSize-1))
    }
}
