package Specs.Smoke

import Pages.App_Store.AppStoreAnalysisHomePage
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
import Utilities.Validations.VerifyUtil
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 5/9/2016.
 */
class LaunchFlexReportsSpec extends BaseSpec{

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    VerifyUtil verifyUtil;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
        verifyUtil = new VerifyUtil()
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
        verifyUtil.verify(getReportHeading().equals(FLEX_TRIO_REPORT), "The Report Heading on Report Page is not equal to: "+FLEX_TRIO_REPORT)

        page VariantInterpretationHomePage

        verifyUtil.verify(getNumberOfItems().equals(THREE), "The Number of Items is not equal to: "+THREE)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_TRIO_REPORT_COLUMN_LIST_GEL), "The Default Column Names are not equal to: "+FLEX_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_TRIO_REPORT_COLUMN_LIST), "The Default Column Names are not equal to: "+FLEX_TRIO_REPORT_COLUMN_LIST)
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10, ONE).equals(data.POSITION_DBSNP_VALUE), "The Position DBSNP is not equal to: "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10, ONE).equals(data.CHANGE_VALUE), "The Change Based On Variant TTLL10 on is not equal to: "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10, ONE).equals(MISSENSE), "The Effect Based on Variant TTLL10 is not equal to: "+MISSENSE)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10, ONE).equals(data.VVP_CADD_VALUE), "The VVPCADDB Based on Variant is not equal to: "+data.VVP_CADD_VALUE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(FLEX_TRIO_REPORT), "The Report Heading on Report Page is not equal to: "+FLEX_TRIO_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(ZERO), "The Number of Items is not equal to: "+ZERO+" in DE NOVO Tab")
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_TRIO_REPORT_COLUMN_LIST_GEL), "The Default Column Names is not equal to: "+FLEX_TRIO_REPORT_COLUMN_LIST_GEL)
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_TRIO_REPORT_COLUMN_LIST), "The Default Column Name is not equal to: "+FLEX_TRIO_REPORT_COLUMN_LIST)
        }

        page VariantReportPage
        verifyUtil.verify(getResponseCodeForExportReportRequest().equals(200), "The Response Code for Export Report Request is not equal to: "+200)
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(FLEX_TRIO_REPORT)
        verifyUtil.verify(getNumberOfReports().equals(reportSize -1), "The Number of Reports on Projects Page is not equal to: "+reportSize-1+" after deletion of the Report")
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
        verifyUtil.verify(getReportHeading().equals(FLEX_QUAD_REPORT), "The Report Heading on Report Page is not equal to: "+FLEX_QUAD_REPORT)

        page VariantInterpretationHomePage

        verifyUtil.verify(getNumberOfItems().equals(THREE), "The Number of Items is not equal to: "+THREE)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_QUAD_REPORT_COLUMN_LIST_GEL), "The Default Column Names are not equal to: "+FLEX_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_QUAD_REPORT_COLUMN_LIST), "The Default Column Names are not equal to: "+FLEX_QUAD_REPORT_COLUMN_LIST)
        }
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10, ONE).equals(data.POSITION_DBSNP_VALUE), "The Position DBSNP is not equal to: "+data.POSITION_DBSNP_VALUE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10, ONE).equals(data.CHANGE_VALUE), "The Change Based On Variant TTLL10 on is not equal to: "+data.CHANGE_VALUE)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10, ONE).equals(MISSENSE), "The Effect Based on Variant TTLL10 is not equal to: "+MISSENSE)
        verifyUtil.verify(getVVPCADDBasecOnVariant(TTLL10, ONE).equals(data.VVP_CADD_VALUE), "The VVPCADDB Based on Variant is not equal to: "+data.VVP_CADD_VALUE)

        page VariantReportPage
        clickOnHeaderButton(DE_NOVO)
        waitForTheReportToAppearWithNoOpacity()
        verifyUtil.verify(getReportHeading().equals(FLEX_QUAD_REPORT), "The Report Heading on Report Page is not equal to: "+FLEX_QUAD_REPORT)
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(ZERO), "The Number of Items is not equal to: "+ZERO+" in DE NOVO Tab")
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_QUAD_REPORT_COLUMN_LIST_GEL), "The Default Column Names is not equal to: "+FLEX_QUAD_REPORT_COLUMN_LIST_GEL)
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(FLEX_QUAD_REPORT_COLUMN_LIST), "The Default Column Name is not equal to: "+FLEX_QUAD_REPORT_COLUMN_LIST)
        }

        page VariantReportPage
        verifyUtil.verify(getResponseCodeForExportReportRequest().equals(200), "The Response Code for Export Report Request is not equal to: "+200)
        driver.navigate().back()

        at ProjectsPage
        int reportSize = getNumberOfReports()
        deleteReport(FLEX_QUAD_REPORT)
        verifyUtil.verify(getNumberOfReports().equals(reportSize -1), "The Number of Reports on Projects Page is not equal to: "+reportSize-1+" after deletion of the Report")
    }
}
