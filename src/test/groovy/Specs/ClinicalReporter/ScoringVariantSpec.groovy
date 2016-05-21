package Specs.ClinicalReporter

import Pages.Clinical_Reporter.VariantInterpretationHomePage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
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
        /* PROJECT_NAME = PROJECT__NAME + data.random;
         WORKSPACE_NAME = ACMG_AUTOMATION_WORKSPACE + data.random

         to LoginPage
         signIn();

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
         signOut()*/
    }

    @Test(groups = ["clinical_reporter", "acmg"], priority = 1)
    public void testScoringVariantsInACMGRepport() {

        to LoginPage
        signIn();

        at OmiciaHomePage

        switchWorkspace("ACMG_Automation_Workspace_-2086695893 ");
        go("http://dev1.omicia-private.com/clinical_reporter/#/build_report/5607/interpret_variants")
        at VariantInterpretationHomePage
        openScoreVariantsBasedOnVariantName(AGRN)

        /*
        switchWorkspace(WORKSPACE_NAME)openTab(UPLOAD_GENOMES);
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
            Assert.assertEquals(getDefaultColumnNamesOnPage(), PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE_ACMG_GEL)
        } else {
            Assert.assertEquals(getDefaultColumnNamesOnPage(), PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE_ACMG)
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
        editCondition(data.EDIT_CONDITION_GENE_LIST)
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
        Assert.assertEquals(getInferredClassification(), UNSCORED, "Default Score in Score Variant before adding a condition Gene is not: 'Unscored'")
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
        Assert.assertEquals(verifyNumberOfCitationsOnCitationsTab(),ONE,"Citation Count on Citations Tab is incorrect")
        clickOnHeaderTab(SCORE_VARIANT)

        at ScoreVariantPage
        clickOnTab(VARIANT_HISTORY)
        Assert.assertEquals(verifyNumberOfHistoryRows(), FOUR, "Variant History rows are not equal to FOUR")

        at VariantInterpretationHomePage
        Assert.equals(getClassConditionBasedOnVariant(AGRN).contains(CLINVAR_OMIM_CONDITION_NAME))
        Assert.equals(getScoringStatusBasedOnVariant(AGRN).equals(SCORING))
        Assert.equals(getLatestClassificationBasedOnVariant(AGRN).contains(SCORING_IN_PROGRESS))*/

    }
}
