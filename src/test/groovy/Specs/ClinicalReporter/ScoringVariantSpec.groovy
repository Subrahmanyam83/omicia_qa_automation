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
import Utilities.Validations.VerifyUtil
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by E002183 on 5/12/2016.
 */
class ScoringVariantSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    public String WORKSPACE_NAME;
    VerifyUtil verifyUtil;
    public String currentMethod;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + generateRandom();
        WORKSPACE_NAME = ACMG_AUTOMATION_WORKSPACE + generateRandom()
        verifyUtil = new VerifyUtil()

        getEreportTest().log(INFO,"Test Case is executing with WORKSPACE: "+WORKSPACE_NAME+" and PROJECT: "+PROJECT_NAME)

        to LoginPage
        signIn();

        at HeaderPage
        createNewWorkspace(WORKSPACE_NAME)
        signOut()

        to LoginPage
        loginWithUser(ADMIN)

        at HeaderPage
        clickOnOPALAdminAndChooseTab(MANAGE_WORKSPACES)

        at ManageWorkspacePage
        search(WORKSPACE_NAME)
        String WID = getIDBasedOnWorkspaceName(WORKSPACE_NAME)
        clickManageBasedOnNameOrId(WORKSPACE_NAME)
        verifyUtil.verify(getHeaderTextOnManageWorkspacePage().equals("Workspace ID " + WID + ": " + WORKSPACE_NAME),"Header text on manage workspace is not matching with Workspace ID " + WID + ": " + WORKSPACE_NAME)
        goToTab(GROUPS)
        clickOnCheckBoxUnderGroupsTab([CLINICAL_REPORTER_ACCESS])
        goToTab(PAYMENT_INFO)
        addPOAccount()

        at HeaderPage
        signOut()
    }

    @Test(groups = ["clinical_reporter", "acmg"], description = "Scoring Variants for ACMG Panel Report")
    public void testScoringVariantsInACMGPanelReport(Method method) {

        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        switchWorkspace(WORKSPACE_NAME)
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
        verifyUtil.verify(getNamesOfGenesAdded().equals(AGRN), "The Gene "+ AGRN+" is not present in the list")
        verifyUtil.verify(getNumberOfPanelGenes().equals(ONE), "Total number of genes shown in Panel Builder are not equal to "+ONE)

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
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(EIGHTEEN),"Total number of items are not equal to "+EIGHTEEN)
        verifyUtil.verify(getEffectBasedOnVariant(AGRN).equals(SYNONYMOUS),"EFFECT of Variant: "+AGRN+" is not equal to "+SYNONYMOUS)
        verifyUtil.verify(getChangeBasedOnVariant(AGRN).equals(data.VARIANT_CHANGE_PANEL_ACMG),"CHANGE of Variant: "+AGRN+" is not equal to "+data.VARIANT_CHANGE_PANEL_ACMG)
        verifyUtil.verify(getClassConditionBasedOnVariant(AGRN).equals(NONE),"CLASS of Variant: "+AGRN+" is not equal to "+NONE)
        verifyUtil.verify(getScoringStatusBasedOnVariant(AGRN).equals(NONE),"Scoring status of Variant: "+AGRN+" is not equal to "+NONE)
        verifyUtil.verify(getReportSectionBasedOnVariant(AGRN).equals(NONE),"Report of Variant: "+AGRN+" is not equal to "+NONE)
        verifyUtil.verify(getLatestClassificationBasedOnVariant(AGRN).equals("-"),"Classification of Variant: "+AGRN+" is not equal to -")
        openScoreVariantsBasedOnVariantName(AGRN)

        at ConditionGenePage
        verifyUtil.verify(getActiveHeaderTab(CONDITION_GENE),"Active Header tab "+CONDITION_GENE+" is not displayed")
        verifyUtil.verify(getActiveTabUnderConditionGeneTab(CLINIVAR_OMIM),"Active tab "+ CLINIVAR_OMIM+" under Condition Gene tab is not displayed")
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
        verifyUtil.verify(getnumberOfWorkSpaceConditionRows().equals(TWO),"Number of workspace conditions rows are not equal to "+ TWO)
        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(CLINVAR_OMIM_CONDITION_NAME, DELETE)
        verifyUtil.verify(getnumberOfWorkSpaceConditionRows().equals(ONE),"Number of workspace conditions rows are not equal to "+ ONE)

        verifyUtil.verify(getInheritanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(NONE), "Inheritance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+NONE)
        verifyUtil.verify(getPrevalanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(PREVALANCE_VALUE), "Prevalance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+PREVALANCE_VALUE)
        verifyUtil.verify(getAgeOfOnsetBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(NEONATAL),"Age of Onset for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+NEONATAL)
        verifyUtil.verify(getPenetranceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(NONE), "Penetrance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+NONE)
        verifyUtil.verify(getNotesBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME, false).equals(NONE),"Notes for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+NONE)
        verifyUtil.verify(getPMIDBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(NONE),"PMID for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+NONE)

        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(CLINVAR_OMIM_CONDITION_NAME, EDIT)
        editConditionGene(data.EDIT_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)

        verifyUtil.verify(getNotesBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(1)),"Notes for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(1))
        verifyUtil.verify(getPMIDBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(2)), "PMID for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(2))
        verifyUtil.verify(getInheritanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(3)), "Inheritance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(3))
        verifyUtil.verify(getPrevalanceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(4)), "Prevalance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(4))
        verifyUtil.verify(getPenetranceBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(5)), "Penetrance for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(5))
        verifyUtil.verify(getAgeOfOnsetBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME).equals(data.EDIT_CONDITION_GENE_LIST.get(6)), "Age of Onset for Condition: "+CLINVAR_OMIM_CONDITION_NAME+" is not equal to "+data.EDIT_CONDITION_GENE_LIST.get(6))
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        verifyUtil.verify(getScoringHeader().equals(UNSCORED), "Default Score in Score Variant before adding a condition Gene is not: 'Unscored'")
        clickOnHeaderTab(CONDITION_GENE)

        at ConditionGenePage
        clickOnCheckBoxBasedOnCondition(CLINVAR_OMIM_CONDITION_NAME)

        at ScoreVariantPage
        verifyUtil.verify(getInferredClassification().equals(UNCERTAIN_SIGNIFICANCE), "Default Classification in Score Variant Page is not '" + UNCERTAIN_SIGNIFICANCE + "'")
        addVariantDescription()
        addInternalNote()
        verifyUtil.verify(verifyTextOfNote().equals(INTERNAL_NOTES), "Internal Notes Text is not matching in Score Variant Tab")
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(THREE), "Variant History rows are not equal to "+ THREE)
        clickOnTab(SCORING_SUMMARY)
        verifyScoringSummaryDefaultText()
        clickOnHeaderTab(CITATIONS)

        at CitationsPage
        addNewCitationsButton(COSEGREGATION)
        verifyUtil.verify(verifyNumberOfCitationsOnCitationsTab().equals(ONE), "Citation Count on Citations Tab is not equal to "+ ONE)
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(FOUR), "Variant History rows are not equal to "+FOUR)

        at VariantInterpretationHomePage
        verifyUtil.verify(getClassConditionBasedOnVariant(AGRN).contains(CLINVAR_OMIM_CONDITION_NAME), "CLASS of Variant: "+AGRN+" does not contain "+CLINVAR_OMIM_CONDITION_NAME)
        verifyUtil.verify(getScoringStatusBasedOnVariant(AGRN).equals(SCORING), "Scoring status of Variant: "+AGRN+" is not equal to "+SCORING)
        verifyUtil.verify(getLatestClassificationBasedOnVariant(AGRN).contains(SCORING_IN_PROGRESS),"Classification of Variant: "+AGRN+" is not equal to "+SCORING_IN_PROGRESS)

        at ScoreVariantPage
        startScoring(data.CRITERION_SCORING_LIST)
        verifyUtil.verify(getCurrentCriterion().equals(data.CRITERION_COMPLETE_TEXT), "Current criteria is not equal to "+data.CRITERION_COMPLETE_TEXT)
        verifyUtil.verify(getScoringProgressText().equals(data.CRITERION_PROGRESS_TEXT), "Scoring Progress text is not equal to "+data.CRITERION_PROGRESS_TEXT)
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(TWENTY_SIX), "Number of history rows are not equal to "+TWENTY_SIX)
        clickOnTab(SCORING_SUMMARY)
        setClassification()
        verifyUtil.verify(getInferredClassification().equals(BENIGN), "Inferred Classification is not equal to "+ BENIGN)
        verifyUtil.verify(getAssignedClassification().equals(BENIGN), "Assigned classification is not equal to "+BENIGN)

        at VariantInterpretationHomePage
        verifyUtil.verify(getClassConditionBasedOnVariant(AGRN).contains(BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME),"CLASS of Variant: "+AGRN+ " does not contain "+ BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME)
        verifyUtil.verify(getScoringStatusBasedOnVariant(AGRN).equals(CLASSIFIED),"Scoring status of Variant: "+AGRN+" is not equal to "+CLASSIFIED)
        verifyUtil.verify(getReportSectionBasedOnVariant(AGRN).equals(NOT_REPORTED),"Report of Variant: "+AGRN+" is not equal to "+ NOT_REPORTED)
        changeReportSectionFromDropDown(AGRN)
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getNumberOfSecondaryFindingReports().equals(ZERO),"Secondary Finding Reports size is not equal to "+ZERO)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200")
    }

    @Test(groups = ["clinical_reporter", "acmg"], description = "Scoring Variants for ACMG Quad Report with No Default Condition Genes.")
    public void testScoringVariantsInACMGQuadReportWithNoDefaultConditionGene(Method method) {


        currentMethod = method.name
        to LoginPage
        signIn();

        at HeaderPage
        switchWorkspace(WORKSPACE_NAME)
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
        verifyUtil.verify(getNamesOfGenesAdded().equals(TTLL10), "The Gene "+ TTLL10+" is not present in the list")
        verifyUtil.verify(getNumberOfPanelGenes().equals(ONE), "Number of Panel genes are not equal to "+ONE)

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
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_QUAD_GEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE), "Variant Interpretation column names are not matching. Expected: "+ ACMG_QUAD_GEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE), "Variant Interpretation column names are not matching. Expected: "+ ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(THREE), "Total number of items are not equal to "+THREE)
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10).equals(data.positiondbSNP_QUAD),"DBSNP Position of Variant: "+TTLL10+" is not equal to "+data.positiondbSNP_QUAD)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD_ACMG),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_QUAD_ACMG)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE_STRING), "VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE_STRING)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(NONE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+NONE)
        verifyUtil.verify(getLatestClassificationBasedOnVariant(TTLL10).equals("-"),"Classification of Variant: "+TTLL10+" is not equal to -")

        runPhevor(ATAXIA)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getAvailableWindows().size() == ONE, "Windows size is not equal to "+ONE)

        clickVariantChromosomePosition(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            verifyUtil.verify(driver.getCurrentUrl().contains("genome.ucsc.edu/"),"Current URL does not contains 'genome.ucsc.edu/'")
            driver.close()
        }
        clickVariantChromosomeDBSNP(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            verifyUtil.verify(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"),"Current URL does not contains 'ncbi.nlm.nih.gov'")
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
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching with "+ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching with "+ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE)
        }
        verifyUtil.verify(getNumberOfItems().equals(ONE),"Number of items are not equal to "+ONE)
        verifyUtil.verify(getPositionDBSNPBasedOnVariant(TTLL10).equals(data.positiondbSNP_QUAD), "DBSNP Position of Variant: "+TTLL10+" is not equal to "+data.positiondbSNP_QUAD)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD_ACMG), "CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_QUAD_ACMG)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE_STRING),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE_STRING)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE.toString()), "Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getInheritanceMode(TTLL10).equals(RECESSIVE),"Inheritance mode for Condition: "+TTLL10+" is not equal to "+RECESSIVE)
        verifyUtil.verify(getLatestClassificationBasedOnVariant(TTLL10).equals("-"), "Classification of Variant: "+TTLL10+" is not equal to -")

        verifyUtil.verify(getAvailableWindows().size() == ONE, "Windows size is not equal to "+ONE)

        clickVariantChromosomePosition(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            verifyUtil.verify(driver.getCurrentUrl().contains("genome.ucsc.edu/"),"Current URL does not contains 'genome.ucsc.edu/'")
            driver.close()
        }
        clickVariantChromosomeDBSNP(TTLL10)
        withWindow(getAvailableWindows().getAt(1).toString()){
            verifyUtil.verify(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"),"Current URL does not contains 'ncbi.nlm.nih.gov'")
            driver.close()
        }

        clickOnEffectBasedOnVariant(TTLL10)
        closeModalPopup()
        verifyUtil.verify(getNumberOfCheckBoxesInShowHideColumns().equals(TWENTY_THREE),"Number of checkboxes in showhide columns are not equal to "+ TWENTY_THREE)

        openScoreVariantsBasedOnVariantName(TTLL10)

        at ConditionGenePage
        verifyUtil.verify(getActiveHeaderTab(CONDITION_GENE),"Active Header tab "+CONDITION_GENE+" is not displayed")
        verifyUtil.verify(getActiveTabUnderConditionGeneTab(CLINIVAR_OMIM),"Active tab "+ CLINIVAR_OMIM+" under Condition Gene tab is not displayed")
        verifyContentUnderConditionGeneTabs(CLINIVAR_OMIM)
        clickOnTabUnderConditionGenes(NLP_PHENOTYPE)
        verifyContentUnderConditionGeneTabs(NLP_PHENOTYPE)
        clickOnTabUnderConditionGenes(WORKSPACE_CONDITION_GENES)
        verifyContentUnderConditionGeneTabs(WORKSPACE_CONDITION_GENES)
        addNewConditionGene()
        editConditionGene(data.CREATE_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)
        verifyUtil.verify(getnumberOfWorkSpaceConditionRows().equals(ONE),"Work space condition rows are not equal to "+ONE)
        verifyUtil.verify(getNotesBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(1)),"Notes for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(1))
        verifyUtil.verify(getPMIDBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(2)),"PMID for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(2))
        verifyUtil.verify(getInheritanceBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(3)),"Inheritance for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(3))
        verifyUtil.verify(getPrevalanceBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(4)),"Prevalance for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(4))
        verifyUtil.verify(getPenetranceBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(5)),"Penetrance for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(5))
        verifyUtil.verify(getAgeOfOnsetBasedOnCondition(FEVER).equals(data.CREATE_CONDITION_GENE_LIST.get(6)),"Age of Onset for Condition: "+FEVER+" is not equal to "+data.CREATE_CONDITION_GENE_LIST.get(6))

        clickOnNewConditionGeneButton()
        editConditionGene(data.CREATE_CONDITION_GENE_LIST)
        clickSaveOrCancel(SAVE)
        waitTillYouAreInActiveTab(WORKSPACE_CONDITION_GENES)
        verifyUtil.verify(getnumberOfWorkSpaceConditionRows().equals(TWO),"Work space condition rows are not equal to "+TWO)
        clickOnActionsButtonAndPerformActionInWorkspaceConditionGenes(FEVER, DELETE)
        verifyUtil.verify(getnumberOfWorkSpaceConditionRows().equals(ONE),"Work space condition rows are not equal to "+ONE)

        clickPMIDBasedOnCondition(FEVER)
        withWindow(getAvailableWindows().getAt(1).toString()){
            verifyUtil.verify(driver.getCurrentUrl().contains("ncbi.nlm.nih.gov"),"Current URL does not contains 'ncbi.nlm.nih.gov'")
            driver.close()
        }
        clickOnHeaderTab(SCORE_VARIANT)
        at ScoreVariantPage
        verifyUtil.verify(getScoringHeader().equals(UNSCORED), "Default Score in Score Variant before adding a condition Gene is not "+UNSCORED)
        clickOnHeaderTab(CONDITION_GENE)

        at ConditionGenePage
        clickOnCheckBoxBasedOnCondition(FEVER)

        at ScoreVariantPage
        verifyUtil.verify(getInferredClassification().equals(UNCERTAIN_SIGNIFICANCE), "Inferred Classification in Score Variant Page is not '" + UNCERTAIN_SIGNIFICANCE + "'")
        addVariantDescription()

        addInternalNote()
        verifyUtil.verify(verifyTextOfNote().contains(INTERNAL_NOTES), "Internal Notes Text does not contain "+INTERNAL_NOTES)
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(THREE), "Variant History rows are not equal to "+THREE)
        clickOnTab(SCORING_SUMMARY)
        verifyScoringSummaryDefaultText()
        clickOnHeaderTab(CITATIONS)

        at CitationsPage
        addNewCitationsButton(COSEGREGATION)
        verifyUtil.verify(verifyNumberOfCitationsOnCitationsTab().equals(ONE), "Citation Count on Citations Tab is not equal to "+ONE)
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(FOUR), "Variant History rows are not equal to "+FOUR)

        at VariantInterpretationHomePage
        verifyUtil.verify(getClassConditionBasedOnVariant(TTLL10).contains(CLINVAR_OMIM_CONDITION_NAME),"CLASS of Variant: "+TTLL10+ " does not contain "+ CLINVAR_OMIM_CONDITION_NAME)
        verifyUtil.verify(getScoringStatusBasedOnVariant(TTLL10).equals(SCORING),"Scoring status of Variant: "+TTLL10+" is not equal to "+SCORING)
        verifyUtil.verify(getLatestClassificationBasedOnVariant(TTLL10).contains(SCORING_IN_PROGRESS), "Classification of Variant: "+TTLL10+" is not equal to "+SCORING_IN_PROGRESS)

        at ScoreVariantPage
        startScoring(data.CRITERION_SCORING_LIST)
        verifyUtil.verify(getCurrentCriterion().equals(data.CRITERION_COMPLETE_TEXT), "Current criteria is not equal to "+data.CRITERION_COMPLETE_TEXT)
        verifyUtil.verify(getScoringProgressText().equals(data.CRITERION_PROGRESS_TEXT),"Scoring Progress text is not equal to "+data.CRITERION_PROGRESS_TEXT)
        clickOnTab(VARIANT_HISTORY)
        verifyUtil.verify(verifyNumberOfHistoryRows().equals(TWENTY_SIX), "Variant History rows are not equal to "+TWENTY_SIX)
        clickOnTab(SCORING_SUMMARY)
        setClassification()
        verifyUtil.verify(getInferredClassification().equals(BENIGN),"Inferred Classification is not equal to "+ BENIGN)
        verifyUtil.verify(getAssignedClassification().equals(BENIGN),"Assigned classification is not equal to "+BENIGN)

        at VariantInterpretationHomePage
        verifyUtil.verify(getClassConditionBasedOnVariant(TTLL10).contains(BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME),"CLASS of Variant: "+TTLL10+ " does not contain "+ BENIGN.replace("\n"," ")+CLINVAR_OMIM_CONDITION_NAME)
        verifyUtil.verify(getScoringStatusBasedOnVariant(TTLL10).equals(CLASSIFIED),"Scoring status of Variant: "+TTLL10+" is not equal to "+CLASSIFIED)
        verifyUtil.verify(getReportSectionBasedOnVariant(TTLL10).equals(NOT_REPORTED),"Report of Variant: "+TTLL10+" is not equal to "+ NOT_REPORTED)
        changeReportSectionFromDropDown(TTLL10)
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getNumberOfSecondaryFindingReports().equals(ZERO),"Secondary Finding Reports size is not equal to "+ZERO)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200")
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodExecution(){
        verifyUtil.assertTestResult("Test Case '"+currentMethod+"' Assertions Failed :")
    }
}
