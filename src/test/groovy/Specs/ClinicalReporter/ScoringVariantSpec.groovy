package Specs.ClinicalReporter

import Pages.Admin.ManageWorkspacePage
import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Clinical_Reporter.ReviewReportPage
import Pages.Clinical_Reporter.ScoringVariant.CitationsPage
import Pages.Clinical_Reporter.ScoringVariant.ConditionGenePage
import Pages.Clinical_Reporter.ScoringVariant.ScoreVariantPage
import Pages.Clinical_Reporter.VariantInterpretationHomePage
import Pages.Clinical_Reporter.VariantSelectionPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Panel_Builder.AddGenesToPanelPage
import Pages.Panel_Builder.CuratePanelPage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

/**
 * Created by E002183 on 5/12/2016.
 */
class ScoringVariantSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    public String WORKSPACE_NAME;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom();
        WORKSPACE_NAME = ACMG_AUTOMATION_WORKSPACE + generateRandom()

        getEreportTest().log(INFO,"Test Case is executing with WORKSPACE: "+WORKSPACE_NAME+" and PROJECT: "+PROJECT_NAME)

        to LoginPage
        signIn();

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        createNewWorkspace(WORKSPACE_NAME)

        at HeaderPage
        signOut()

        to LoginPage
        loginWithUser(ADMIN)

        at HeaderPage
        clickOnOPALAdminAndChooseTab(MANAGE_WORKSPACES)

        at ManageWorkspacePage
        search(WORKSPACE_NAME)
        String WID = getIDBasedOnWorkspaceName(WORKSPACE_NAME)
        clickManageBasedOnNameOrId(WORKSPACE_NAME)
        Assert.assertEquals(getHeaderTextOnManageWorkspacePage(), "Workspace ID " + WID + ": " + WORKSPACE_NAME)
        goToTab(GROUPS)
        clickOnCheckBoxUnderGroupsTab([CLINICAL_REPORTER_ACCESS])
        goToTab(PAYMENT_INFO)
        addPOAccount()

        at HeaderPage
        signOut()
    }

    @Test(groups = ["clinical_reporter", "acmg"], description = "Scoring Variants for ACMG Panel Report")
    public void testScoringVariantsInACMGPanelReport() {

        to LoginPage
        signIn();

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        switchWorkspace(WORKSPACE_NAME)

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);
        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)

        at HeaderPage
        clickOnMenuAndSelectOption(PANEL_BUILDER)

        at PanelBuilderPage
        createNewPanel(data.PANEL_NAME, data.PANEL_DESCRIPTION)
        clickOnActionsButtonBasedOnAndClickAction(data.PANEL_NAME, CURATE_PANEL)

        at CuratePanelPage
        chooseFilter(PROTIEN_FILTER);
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        addGenesByGivingSymbols(AGRN)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        waitFor { curatePanel.getNumberOfPanelGenes }
        Assert.assertEquals(getNamesOfGenesAdded(), AGRN, "The Gene(s) added in Panel Page is different in Curate Panel Page")
        Assert.assertEquals(getNumberOfPanelGenes(), ONE)

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(PANEL)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME, data.PANEL_NAME)
        chooseGeneForEachMember(PANEL)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        }
        Assert.assertEquals(getNumberOfItems(), EIGHTEEN)
        Assert.equals(getEffectBasedOnVariant(AGRN).equals(SYNONYMOUS))
        Assert.equals(getChangeBasedOnVariant(AGRN).equals(data.VARIANT_CHANGE_PANEL_ACMG))
        Assert.equals(getClassConditionBasedOnVariant(AGRN).equals(NONE))
        Assert.equals(getScoringStatusBasedOnVariant(AGRN).equals(NONE))
        Assert.equals(getReportSectionBasedOnVariant(AGRN).equals(NONE))
        Assert.equals(getLatestClassificationBasedOnVariant(AGRN).equals("-"))
        openScoreVariantsBasedOnVariantName(AGRN)

        at ConditionGenePage
        Assert.assertTrue(getActiveHeaderTab(CONDITION_GENE))
        Assert.assertTrue(getActiveTabUnderConditionGeneTab(CLINIVAR_OMIM))
        verifyContentUnderConditionGeneTabs(CLINIVAR_OMIM)
        clickOnTabUnderConditionGenes(NLP_PHENOTYPE)
        verifyContentUnderConditionGeneTabs(NLP_PHENOTYPE)
        clickOnTabUnderConditionGenes(WORKSPACE_CONDITION_GENES)
        verifyContentUnderConditionGeneTabs(WORKSPACE_CONDITION_GENES)
        clickOnTabUnderConditionGenes(CLINIVAR_OMIM)
        clickOnColumnBasedOnConditionUnderClinVarAndOminTab(CLINVAR_OMIM_CONDITION_NAME, COPY_TO_WORKSPACE, 1)
        clickSaveOrCancel(SAVE)
        clickOnTabUnderConditionGenes(CLINIVAR_OMIM)
        clickOnColumnBasedOnConditionUnderClinVarAndOminTab(CLINVAR_OMIM_CONDITION_NAME, COPY_TO_WORKSPACE, 1)
        clickSaveOrCancel(SAVE)
        Assert.assertEquals(getnumberOfWorkSpaceConditionRows(), TWO)
        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(CLINVAR_OMIM_CONDITION_NAME, DELETE)
        Assert.assertEquals(getnumberOfWorkSpaceConditionRows(), ONE)

        Assert.assertEquals(getInheritanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), NONE)
        Assert.assertEquals(getPrevalanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), PREVALANCE_VALUE)
        Assert.assertEquals(getAgeOfOnsetBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), NEONATAL)
        Assert.assertEquals(getPenetranceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), NONE)
        Assert.assertEquals(getNotesBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME, false), NONE)
        Assert.assertEquals(getPMIDBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), NONE)

        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(CLINVAR_OMIM_CONDITION_NAME, EDIT)
        editConditionGene(data.EDIT_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)

        Assert.assertEquals(getNotesBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(1))
        Assert.assertEquals(getPMIDBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(2))
        Assert.assertEquals(getInheritanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(3))
        Assert.assertEquals(getPrevalanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(4))
        Assert.assertEquals(getPenetranceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(5))
        Assert.assertEquals(getAgeOfOnsetBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME), data.EDIT_CONDITION_GENE_LIST.get(6))
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        Assert.assertEquals(getScoringHeader(), UNSCORED, "Default Score in Score Variant before adding a condition Gene is not: 'Unscored'")
        clickOnHeaderTab(CONDITION_GENE)

        at ConditionGenePage
        clickOnCheckBoxBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME)

        at ScoreVariantPage
        Assert.assertEquals(getInferredClassification(), UNCERTAIN_SIGNIFICANCE, "Default Classification in Score Variant Page is not '" + UNCERTAIN_SIGNIFICANCE + "'")
        addVariantDescription()
        addInternalNote()
        Assert.assertEquals(verifyTextOfNote(), INTERNAL_NOTES, "Internal Notes Text is not matching in Score Variant Tab")
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), THREE, "Variant History rows are not equal to Three")
        clickOnTab(SCORING_SUMMARY)
        verifyScoringSummaryDefaultText()
        clickOnHeaderTab(CITATIONS)

        at CitationsPage
        addNewCitationsButton(COSEGREGATION)
        Assert.assertEquals(verifyNumberOfCitationsOnCitationsTab(), ONE, "Citation Count on Citations Tab is incorrect")
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), FOUR, "Variant History rows are not equal to FOUR")

        at VariantInterpretationHomePage
        Assert.equals(getClassConditionBasedOnVariant(AGRN).contains(CLINVAR_OMIM_CONDITION_NAME))
        Assert.equals(getScoringStatusBasedOnVariant(AGRN).equals(SCORING))
        Assert.equals(getLatestClassificationBasedOnVariant(AGRN).contains(SCORING_IN_PROGRESS))

        at ScoreVariantPage
        startScoring(data.CRITERION_SCORING_LIST)
        Assert.assertEquals(getCurrentCriterion(),data.CRITERION_COMPLETE_TEXT)
        Assert.assertEquals(getScoringProgressText(),data.CRITERION_PROGRESS_TEXT)
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), TWENTY_SIX, "Variant History rows are not equal to FOUR")
        clickOnTab(SCORING_SUMMARY)
        setClassification()
        Assert.assertEquals(getInferredClassification(),BENIGN)
        Assert.assertEquals(getAssignedClassification(),BENIGN)

        at VariantInterpretationHomePage
        Assert.equals(getClassConditionBasedOnVariant(AGRN).contains(BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME))
        Assert.equals(getScoringStatusBasedOnVariant(AGRN).equals(CLASSIFIED))
        Assert.assertEquals(getReportSectionBasedOnVariant(AGRN),NOT_REPORTED)
        changeReportSectionFromDropDown(AGRN)
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.equals(getNumberOfSecondaryFindingReports().equals(ZERO))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }

    @Test(groups = ["clinical_reporter", "acmg"], description = "Scoring Variants for ACMG Quad Report with No Default Condition Genes.")
    public void testScoringVariantsInACMGQuadReportWithNoDefaultConditionGene() {

        to LoginPage
        signIn();

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        switchWorkspace(WORKSPACE_NAME)

        at HeaderPage
        goToHomePage()

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

        at UploadGenomePage
        fillUploadGenomeForm(PROJECT_NAME, true, true, data.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(PROJECT_NAME, FOUR)

        at HeaderPage
        clickOnMenuAndSelectOption(PANEL_BUILDER)

        at PanelBuilderPage
        createNewPanel(data.PANEL_NAME, data.PANEL_DESCRIPTION)
        clickOnActionsButtonBasedOnAndClickAction(data.PANEL_NAME, CURATE_PANEL)

        at CuratePanelPage
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        addGenesByGivingSymbols(TTLL10)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        waitFor { curatePanel.getNumberOfPanelGenes }
        Assert.assertEquals(getNamesOfGenesAdded(), TTLL10, "The Gene(s) added in Panel Page is different in Curate Panel Page")
        Assert.assertEquals(getNumberOfPanelGenes(), ONE)

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(QUAD)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME, data.PANEL_NAME)
        chooseGeneForEachMember(QUAD)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_QUAD_GEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE)
        }
        Assert.assertEquals(getNumberOfItems(), THREE)
        Assert.equals(getPositionDBSNPBasedOnVariant(TTLL10).equals(data.positiondbSNP_QUAD))
        Assert.equals(getEffectBasedOnVariant(TTLL10).equals(MISSENSE))
        Assert.equals(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD_ACMG))
        Assert.equals(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE_STRING))
        Assert.equals(getPhevorRankBasedOnVariant(TTLL10).equals(NONE))
        Assert.equals(getLatestClassificationBasedOnVariant(TTLL10).equals("-"))

        runPhevor(ATAXIA)
        Assert.equals(getPhevorRankBasedOnVariant(TTLL10).equals(THREE))
        Assert.equals(getAvailableWindows().size() == ONE)

        clickVariantChromosomePosition(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("genome.ucsc.edu/"))
            driver.close()
        }
        clickVariantChromosomeDBSNP(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"))
            driver.close()
        }

        clickOnEffectBasedOnVariant(TTLL10)
        closeModalPopup()

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)) {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        }
        Assert.assertEquals(getNumberOfItems(), ONE)
        Assert.equals(getPositionDBSNPBasedOnVariant(TTLL10).equals(data.positiondbSNP_QUAD))
        Assert.equals(getEffectBasedOnVariant(TTLL10).equals(MISSENSE))
        Assert.equals(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD_ACMG))
        Assert.equals(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE_STRING))
        Assert.equals(getPhevorRankBasedOnVariant(TTLL10).equals(THREE.toString()))
        Assert.equals(getInheritanceMode(TTLL10).equals(RECESSIVE))
        Assert.equals(getLatestClassificationBasedOnVariant(TTLL10).equals("-"))

        Assert.equals(getAvailableWindows().size() == ONE)

        clickVariantChromosomePosition(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("genome.ucsc.edu/"))
            driver.close()
        }
        clickVariantChromosomeDBSNP(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"))
            driver.close()
        }

        clickOnEffectBasedOnVariant(TTLL10)
        closeModalPopup()
        Assert.equals(getNumberOfCheckBoxesInShowHideColumns().equals(TWENTY_THREE))

        openScoreVariantsBasedOnVariantName(TTLL10)

        at ConditionGenePage
        Assert.assertTrue(getActiveHeaderTab(CONDITION_GENE))
        Assert.assertTrue(getActiveTabUnderConditionGeneTab(CLINIVAR_OMIM))
        verifyContentUnderConditionGeneTabs(CLINIVAR_OMIM)
        clickOnTabUnderConditionGenes(NLP_PHENOTYPE)
        verifyContentUnderConditionGeneTabs(NLP_PHENOTYPE)
        clickOnTabUnderConditionGenes(WORKSPACE_CONDITION_GENES)
        verifyContentUnderConditionGeneTabs(WORKSPACE_CONDITION_GENES)
        addNewConditionGene()
        editConditionGene(data.CREATE_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)
        Assert.assertEquals(getnumberOfWorkSpaceConditionRows(), ONE)
        Assert.assertEquals(getNotesBasedOnCondition(FEVER),data.CREATE_CONDITION_GENE_LIST.get(1))
        Assert.assertEquals(getPMIDBasedOnCondition(FEVER), data.CREATE_CONDITION_GENE_LIST.get(2))
        Assert.assertEquals(getInheritanceBasedOnCondition(FEVER), data.CREATE_CONDITION_GENE_LIST.get(3))
        Assert.assertEquals(getPrevalanceBasedOnCondition(FEVER), data.CREATE_CONDITION_GENE_LIST.get(4))
        Assert.assertEquals(getPenetranceBasedOnCondition(FEVER), data.CREATE_CONDITION_GENE_LIST.get(5))
        Assert.assertEquals(getAgeOfOnsetBasedOnCondition(FEVER), data.CREATE_CONDITION_GENE_LIST.get(6))

        clickOnNewConditionGeneButton()
        editConditionGene(data.CREATE_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)
        Assert.assertEquals(getnumberOfWorkSpaceConditionRows(), TWO)
        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(FEVER, DELETE)
        Assert.assertEquals(getnumberOfWorkSpaceConditionRows(), ONE)

        clickPMIDBasedOnCondition(FEVER)
        withWindow(getAvailableWindows().getAt(1).toString()){
            Assert.equals(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"))
            driver.close()
        }
        clickOnHeaderTab(SCORE_VARIANT)
        at ScoreVariantPage
        Assert.assertEquals(getScoringHeader(), UNSCORED, "Default Score in Score Variant before adding a condition Gene is not: 'Unscored'")
        clickOnHeaderTab(CONDITION_GENE)

        at ConditionGenePage
        clickOnCheckBoxBasedOnCondition(FEVER)

        at ScoreVariantPage
        Assert.assertEquals(getInferredClassification(), UNCERTAIN_SIGNIFICANCE, "Default Classification in Score Variant Page is not '" + UNCERTAIN_SIGNIFICANCE + "'")
        addVariantDescription()

        addInternalNote()
        Assert.assertTrue(verifyTextOfNote().contains(INTERNAL_NOTES), "Internal Notes Text is not matching in Score Variant Tab")
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), THREE, "Variant History rows are not equal to Three")
        clickOnTab(SCORING_SUMMARY)
        verifyScoringSummaryDefaultText()
        clickOnHeaderTab(CITATIONS)

        at CitationsPage
        addNewCitationsButton(COSEGREGATION)
        Assert.assertEquals(verifyNumberOfCitationsOnCitationsTab(), ONE, "Citation Count on Citations Tab is incorrect")
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), FOUR, "Variant History rows are not equal to FOUR")

        at VariantInterpretationHomePage
        Assert.equals(getClassConditionBasedOnVariant(TTLL10).contains(CLINVAR_OMIM_CONDITION_NAME))
        Assert.equals(getScoringStatusBasedOnVariant(TTLL10).equals(SCORING))
        Assert.equals(getLatestClassificationBasedOnVariant(TTLL10).contains(SCORING_IN_PROGRESS))

        at ScoreVariantPage
        startScoring(data.CRITERION_SCORING_LIST)
        Assert.assertEquals(getCurrentCriterion(),data.CRITERION_COMPLETE_TEXT)
        Assert.assertEquals(getScoringProgressText(),data.CRITERION_PROGRESS_TEXT)
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), TWENTY_SIX, "Variant History rows are not equal to FOUR")
        clickOnTab(SCORING_SUMMARY)
        setClassification()
        Assert.assertEquals(getInferredClassification(),BENIGN)
        Assert.assertEquals(getAssignedClassification(),BENIGN)

        at VariantInterpretationHomePage
        Assert.equals(getClassConditionBasedOnVariant(TTLL10).contains(BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME))
        Assert.equals(getScoringStatusBasedOnVariant(TTLL10).equals(CLASSIFIED))
        Assert.assertEquals(getReportSectionBasedOnVariant(TTLL10),NOT_REPORTED)
        changeReportSectionFromDropDown(TTLL10)
        clickReviewReport()

        at ReviewReportPage
        Assert.equals(getNumberOfPrimaryFindingReports().equals(ONE))
        Assert.equals(getNumberOfSecondaryFindingReports().equals(ZERO))
        Assert.assertEquals(getResponseCodeForPreviewPDF(), 200);
    }
}
