package Specs.Smoke

import Pages.App_Store.AnalysisHomePage
import Pages.Clinical_Reporter.*
import Pages.Filtering_Protocol.NewFilteringProtocolPage
import Pages.Gene_Sets.GeneSetsPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Panel_Builder.AddGenesToPanelPage
import Pages.Panel_Builder.CuratePanelPage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Projects.ReportsHomePage
import Pages.Projects.VariantReportPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/21/2016.
 */
class SmokeSpec extends BaseSpec{

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;

    @BeforeMethod
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom()
    }

    @Test(groups = "smoke", priority = 1)
    public void launchEndToEndPanelReport() {

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, SHORT_FILE);
        waitForTheVCFFileToUpload();
        fillUploadGenomeForm(PROJECT_NAME, false, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, data.GENE_COUNT)
        clickProjectInProjectsHomePage(PROJECT_NAME);

        at ProjectsPage
        clickOnColumnBasedOnGenomeLabel(data.GENE_LABEL, REPORT_TYPE)

        at VariantReportPage
        getNumberOfGenesOnVariantPage().equals(data.VARIANT_GENE)
        clickOnColumnBasedOnGene(data.GENE_NAME, EFFECT)
        verifyDialogBoxPresentOnClickAndClose()
        clickOnColumnBasedOnGene(data.GENE_NAME, ONE_KG_AF)
        verifyDialogBoxPresentOnClickAndClose()
        clickOnColumnBasedOnGene(data.GENE_NAME, EVIDENCE_CV)
        verifyDialogBoxPresentOnClickAndClose();

        at HeaderPage
        clickOnMenuAndSelectOption(GENE_SETS)

        at GeneSetsPage
        createNewGeneSet(data.GENE_SET_NAME, data.DESCRIPTION, MY_SET);
        performActionBasedOnGeneSetName(MY_SET, data.GENE_SET_NAME, ADD_GENES);
        addGenesToGeneSet(data.GENE_SYMBOLS);

        at HeaderPage
        clickOnMenuAndSelectOption(PANEL_BUILDER)

        at PanelBuilderPage
        createNewPanel(data.PANEL_NAME, data.PANEL_DESCRIPTION)
        clickOnActionsButtonBasedOnAndClickAction(data.PANEL_NAME, CURATE_PANEL)

        at CuratePanelPage
        chooseFilter(PROTIEN_FILTER);
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        addGenesByGivingSymbols(data.GENES_TO_BE_ADDED_AS_SYMBOLS)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        waitFor { curatePanel.getNumberOfPanelGenes }
        Assert.assertEquals(getNumberOfPanelGenes(), THREE)

        at HeaderPage
        clickOnMenuAndSelectOption(FILTERING_PROTOCOL)

        at NewFilteringProtocolPage
        createNewFilteringProtocol()
        fillNameAndDescription(data.FILTERING_PROTOCOL_NAME, data.FILTERING_PROTOCOL_DESCRIPTION)
        chooseConsequenceGenemodelAndExclude(CONSEQUENCE_LIST, [], EVIDENCE_LIST)
        saveFilteringProtocol()

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(PANEL)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME, data.PANEL_NAME, data.FILTERING_PROTOCOL_NAME)
        chooseGeneForEachMember(PANEL)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantInterpretationHomePage
        Assert.equals(getEffectBasedOnVariant(SAMD11).equals(MISSENSE))
        clickOnInterpretVariantBasedOnVariant(SAMD11)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        showHideColumns(TO_REPORT)
        Assert.equals(getClassBasedOnVariant(SAMD11).equals(CLASSIFICATION_PATHOGENIC))
        Assert.equals(getStatusBasedOnVariant(SAMD11).equals(REVIEWED))
        Assert.equals(getReportBasedOnVariant(SAMD11).equals(PRIMARY))
        clickOnInterpretVariantBasedOnVariant(PLEKHN1)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, SECONDARY_FINDING)
        clickOnTab(VARIANT_EVIDENCE)
        clickOnCopyToVariantInterpretationAndDescription()
        Assert.equals(getCurrentVariantClassificationDropDownValue().equals(UNCERTAIN_SIGNIFICANCE))
        Assert.equals(getCurrentConditionTextFieldValue().equals(DUCTAL_BREAST_CARCINOMA))
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.equals(getNumberOfSecondaryFindingReports().equals(ONE))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }

    @Test(groups = "smoke", priority = 2)
    public void launchSoloReport() {

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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(SOLO)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        Assert.equals(getNamesOfMembers().equals(SOLO_LIST))

        chooseGeneForEachMember(SOLO)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), SIXTY_THREE)
        runPhevor(ATAXIA)
        Assert.assertEquals(getDefaultColumnNamesOnPage(), SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        Assert.assertEquals(getNumberOfItems(), SIXTY_THREE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_SOLO)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), TWO)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        Assert.assertEquals(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10), true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        Assert.assertEquals(getDefaultColumnNamesOnPage(), SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        Assert.assertEquals(getNumberOfItems(), ONE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_SOLO)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getClassBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getStatusBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), TWO)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)
        Assert.assertEquals(getInheritanceMode(TTLL10), RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }

    @Test(groups = "smoke", priority = 3)
    public void launchTrioReport() {

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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(TRIO)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        Assert.equals(getNamesOfMembers().equals(TRIO_LIST))

        chooseGeneForEachMember(TRIO)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), FOUR)
        runPhevor(ATAXIA)
        Assert.assertEquals(getDefaultColumnNamesOnPage(), TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        Assert.assertEquals(getNumberOfItems(), FOUR)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_TRIO)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), ONE)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        Assert.assertEquals(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10), true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        Assert.assertEquals(getDefaultColumnNamesOnPage(), TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        Assert.assertEquals(getNumberOfItems(), ONE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_TRIO)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getClassBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getStatusBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), ONE)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)
        Assert.assertEquals(getInheritanceMode(TTLL10), RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }

    @Test(groups = "smoke", priority = 4)
    public void launchQuadReport() {

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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(QUAD)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        Assert.equals(getNamesOfMembers().equals(QUAD_LIST))

        chooseGeneForEachMember(QUAD)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        Assert.assertEquals(getNumberOfItems(), FOUR)
        runPhevor(ATAXIA)
        Assert.assertEquals(getDefaultColumnNamesOnPage(), QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        Assert.assertEquals(getNumberOfItems(), FOUR)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_QUAD)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), ONE)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        Assert.assertEquals(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10), true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        Assert.assertEquals(getDefaultColumnNamesOnPage(), QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        Assert.assertEquals(getNumberOfItems(), ONE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.VARIANT_CHANGE_QUAD)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getClassBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getStatusBasedOnVariant(TTLL10), NONE_TEXT)
        Assert.assertEquals(getVAASTGeneRankBasedOnVariant(TTLL10), ONE)
        Assert.assertEquals(getPhevorRankBasedOnVariant(TTLL10), THREE)
        Assert.assertEquals(getInheritanceMode(TTLL10), RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }

    @Test(groups = "smoke", priority = 5)
    public void launchVAASTSoloAnalysis() {

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
        Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST)
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
        Assert.assertEquals(getDefaultColumnNamesOnPage(), VAAST_SOLO_REPORT_COLUMN_LIST)
        Assert.assertEquals(getPositionDBSNPBasedOnVariant(TTLL10), data.POSITION_DBSNP_VALUE)
        Assert.assertEquals(getChangeBasedOnVariant(TTLL10), data.CHANGE_VALUE)
        Assert.assertEquals(getEffectBasedOnVariant(TTLL10), MISSENSE)
        Assert.assertEquals(getVAASTRankBasedOnVariant(TTLL10), THREE)
        Assert.assertEquals(getVVPCADDBasecOnVariant(TTLL10), data.VVP_CADD_VALUE)
        Assert.assertEquals(getVAASTVScoreBasedOnVariant(TTLL10), FOURTEEN_POINT_THREE_TWO)
        Assert.assertEquals(getVAASTGScoreBasedOnVariant(TTLL10), data.VAAST_G_SCORE_VALUE_DOMINANT)
    }
}
