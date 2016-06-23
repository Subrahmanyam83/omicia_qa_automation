package Specs.ClinicalReporter

import Pages.Clinical_Reporter.*
import Pages.Filtering_Protocol.NewFilteringProtocolPage
import Pages.Gene_Sets.GeneSetsPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Panel_Builder.AddGenesToPanelPage
import Pages.Panel_Builder.CuratePanelPage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.Projects.VariantReportPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Utilities.Data.SmokeTestData
import Utilities.Class.BaseSpec
import Utilities.Validations.VerifyUtil
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import java.lang.reflect.Method

/**
 * Created by E002183 on 4/21/2016.
 */
class LaunchClinicalReportsSpec extends BaseSpec {

    SmokeTestData data = new SmokeTestData();
    public String PROJECT_NAME;
    VerifyUtil verifyUtil;
    public String currentMethod;

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        PROJECT_NAME = PROJECT__NAME + data.random
        verifyUtil = new VerifyUtil()
    }

    @Test(groups = ["smoke", "functional"], priority = 1, description = "Launch End to End Panel Report")
    public void launchEndToEndPanelReport(Method method) {

        currentMethod = method.name;
        to LoginPage
        signIn();

        at HeaderPage
        clickOnMenuAndSelectOption(UPLOAD)

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
        clickOnActionsButtonBasedOnPanelAndClickAction(data.PANEL_NAME, CURATE_PANEL)

        at CuratePanelPage
        chooseFilter(PROTIEN_FILTER);
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        addGenesByGivingSymbols(data.GENES_TO_BE_ADDED_AS_SYMBOLS)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        waitFor { curatePanel.getNumberOfPanelGenes }
        int actualPanelGeneCount = getNumberOfPanelGenes();
        verifyUtil.verify(actualPanelGeneCount.equals(THREE), "Panel Gene count is not equal to "+ THREE)

        at HeaderPage
        clickOnMenuAndSelectOption(FILTERING_PROTOCOL)

        at NewFilteringProtocolPage
        createNewFilteringProtocol()
        fillNameAndDescription(data.FILTERING_PROTOCOL_NAME, data.FILTERING_PROTOCOL_DESCRIPTION)
        chooseConsequenceGenemodelAndExclude(CONSEQUENCE_LIST, [], EVIDENCE_LIST)
        saveFilteringProtocol()
        verifyNewFilteringProtocolIsCreated(data.FILTERING_PROTOCOL_NAME)

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
        List<String> columnNames = getDefaultColumnNamesOnPage();
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(columnNames.equals(PRE_ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(columnNames.equals(PRE_ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE), "Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_PANEL_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(SEVEN), "Total number of items are not equal to "+ SEVEN)
        verifyUtil.verify(getReviewPriority(SAMD11,["orange","gray","green"]).equals(true), "Review Priority Colors are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(SAMD11).equals(HOMOZYGOUS), "Zygosity of the Variant "+SAMD11+" is not HOMOZYGOUS")
        verifyUtil.verify(getQualityGQCoverage(SAMD11).equals(true), "Quality GQ Coverage of the Variant "+SAMD11+" is not matching")
        verifyUtil.verify(getOmiciaScore(SAMD11).equals(true), "Omicia Score of the Variant "+SAMD11+" is not matching")
        verifyUtil.verify(getScoreContainer(SAMD11).equals(true), "Score Container of the Variant "+SAMD11+" is not matching")
        verifyUtil.verify(getEvidence(SAMD11).equals(NONE), "Evidence of the Variant "+SAMD11+" is not matching")
        verifyUtil.verify(getEffectBasedOnVariant(SAMD11).equals(MISSENSE), "EFFECT of Variant: "+SAMD11 +" is not equal to "+MISSENSE)
        verifyUtil.verify(getChangeBasedOnVariant(SAMD11).equals(data.VARIANT_CHANGE_PANEL),"CHANGE of Variant: "+SAMD11 +" is not equal to "+data.VARIANT_CHANGE_PANEL)
        verifyUtil.verify(getClassBasedOnVariant(SAMD11).equals(NONE_TEXT),"CLASS of Variant: "+SAMD11 +" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getStatusBasedOnVariant(SAMD11).equals(NONE_TEXT),"STATUS of Variant: "+SAMD11 +" is not equal to "+NONE_TEXT)

        clickOnInterpretVariantBasedOnVariant(SAMD11)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        showHideColumns(TO_REPORT)
        verifyUtil.verify(getClassBasedOnVariant(SAMD11).equals(CLASSIFICATION_PATHOGENIC),"CLASS of Variant: "+SAMD11 +" is not equal to "+CLASSIFICATION_PATHOGENIC)
        verifyUtil.verify(getStatusBasedOnVariant(SAMD11).equals(REVIEWED), "STATUS of Variant: "+SAMD11 +" is not equal to "+REVIEWED)
        verifyUtil.verify(getReportBasedOnVariant(SAMD11).equals(PRIMARY), "REPORT of Variant: "+SAMD11 +" is not equal to "+PRIMARY)
        clickOnInterpretVariantBasedOnVariant(PLEKHN1)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, SECONDARY_FINDING)
        clickOnTab(VARIANT_EVIDENCE)
        clickOnCopyToVariantInterpretationAndDescription()
        verifyUtil.verify(getCurrentVariantClassificationDropDownValue().equals(UNCERTAIN_SIGNIFICANCE),"Variant classification drop down values are not matching with "+UNCERTAIN_SIGNIFICANCE)
        verifyUtil.verify(getCurrentConditionTextFieldValue().equals(DUCTAL_BREAST_CARCINOMA),"Variant condition text field value is not equal to "+DUCTAL_BREAST_CARCINOMA)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getNumberOfSecondaryFindingReports().equals(ONE),"Secondary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200");
    }

    @Test(groups = ["smoke", "functional"], priority = 2, description = "Launch Solo Report")
    public void launchSoloReport(Method method) {

        currentMethod = method.name;
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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(SOLO)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        verifyUtil.verify(getNamesOfMembers().equals(SOLO_LIST),"Name of the members are not matching with "+SOLO_LIST)

        chooseGeneForEachMember(SOLO)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(SIXTY_THREE),"Total number of Items are not equal to "+ SIXTY_THREE)
        runPhevor(ATAXIA)
        if (baseUrl.contains(GEL)){
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Selection column names are not matching. Expected: "+ PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE), "Variant Selection column names are not matching. Expected: "+ PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(SIXTY_THREE),"Total number of Items are not equal to "+SIXTY_THREE)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","red"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_SOLO),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_SOLO)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(TWO),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+TWO)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        verifyUtil.verify(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10).equals(true),"Checkbox is not set to "+ true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)){
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else{
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE), "Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_SOLO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(ONE),"Total number of Items are not equal to "+ONE)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","red"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_SOLO),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_SOLO)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getClassBasedOnVariant(TTLL10).equals(NONE_TEXT),"CLASS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getStatusBasedOnVariant(TTLL10).equals(NONE_TEXT),"STATUS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(TWO),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+TWO)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getInheritanceMode(TTLL10).equals(RECESSIVE),"Inheritance Mode of Variant: "+TTLL10+" is not equal to "+ RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200")
    }

    @Test(groups = ["smoke", "functional"], priority = 3, description = "Launch Trio Report")
    public void launchTrioReport(Method method) {

        currentMethod = method.name;
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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(TRIO)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        verifyUtil.verify(getNamesOfMembers().equals(TRIO_LIST),"Names of members are not equal to "+TRIO_LIST)

        chooseGeneForEachMember(TRIO)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(FOUR),"Total number of Items are not equal to "+FOUR)
        runPhevor(ATAXIA)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(FOUR),"Total number of Items are not equal to "+FOUR)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","green"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getMotherZygosity(TTLL10).equals(HETROZYGOUS), "Mother Zygosity of the Variant "+TTLL10+" is not "+HETROZYGOUS)
        verifyUtil.verify(getFatherZygosity(TTLL10).equals(NONE), "Father Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_TRIO),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_TRIO)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE), "Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        verifyUtil.verify(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10).equals(true),"Checkbox is not set to "+ true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_TRIO_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(ONE),"Total number of Items are not equal to "+ONE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_TRIO),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_TRIO)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","green"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getMotherZygosity(TTLL10).equals(HETROZYGOUS), "Mother Zygosity of the Variant "+TTLL10+" is not "+HETROZYGOUS)
        verifyUtil.verify(getFatherZygosity(TTLL10).equals(NONE), "Father Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getClassBasedOnVariant(TTLL10).equals(NONE_TEXT),"CLASS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getStatusBasedOnVariant(TTLL10).equals(NONE_TEXT),"STATUS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getInheritanceMode(TTLL10).equals(RECESSIVE),"Inheritance Mode of Variant: "+TTLL10+" is not equal to "+ RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200")
    }

    @Test(groups = ["smoke", "functional"], priority = 4, description = "Launch Quad Report")
    public void launchQuadReport(Method method) {

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

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(QUAD)
        fillDetailsForNewReport(data.PATIENT_ID, PROJECT_NAME)
        verifyUtil.verify(getNamesOfMembers().equals(QUAD_LIST),"Names of the members are not equal to "+QUAD_LIST)

        chooseGeneForEachMember(QUAD)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(data.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(data.PATIENT_ID, INTERPRET_VARIANTS)

        at VariantSelectionPage
        page VariantInterpretationHomePage
        verifyUtil.verify(getNumberOfItems().equals(FOUR),"Total number of items are not equal to "+FOUR)
        runPhevor(ATAXIA)
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_SELECTION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(FOUR),"Total number of items is not equal to "+FOUR)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_QUAD)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","green"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getMotherZygosity(TTLL10).equals(HETROZYGOUS), "Mother Zygosity of the Variant "+TTLL10+" is not "+HETROZYGOUS)
        verifyUtil.verify(getFatherZygosity(TTLL10).equals(NONE), "Father Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getSiblingZygosity(TTLL10).equals(NONE), "Sibling Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)

        page VariantSelectionPage
        clickCheckBoxBasedOnVariant(TTLL10)
        verifyUtil.verify(verifyIfCheckBoxIsCheckedBasedOnVariant(TTLL10).equals(true),"Checkbox is not set to "+true)
        clickAddSelectedVariants()
        clickVariantInterpretationButton()

        at VariantInterpretationHomePage
        if (baseUrl.contains(GEL)) {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_GEL_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        } else {
            verifyUtil.verify(getDefaultColumnNamesOnPage().equals(PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE),"Variant Interpretation column names are not matching. Expected: "+ PRE_ACMG_QUAD_COLUMN_NAMES_IN_VARIANT_INTERPRETATION_PAGE + "Actual: "+ getDefaultColumnNamesOnPage())
        }
        verifyUtil.verify(getNumberOfItems().equals(ONE),"Total number of Items are not equal to "+ONE)
        verifyUtil.verify(getChangeBasedOnVariant(TTLL10).equals(data.VARIANT_CHANGE_QUAD),"CHANGE of Variant: "+TTLL10+" is not equal to "+data.VARIANT_CHANGE_QUAD)
        verifyUtil.verify(getEffectBasedOnVariant(TTLL10).equals(MISSENSE),"EFFECT of Variant: "+TTLL10+" is not equal to "+MISSENSE)
        verifyUtil.verify(getReviewPriority(TTLL10,["orange","gray","green"]).equals(true), "Review Priority Colors of the Variant: +"+TTLL10+"are not equal to "+["orange","gray","green"])
        verifyUtil.verify(getZygosity(TTLL10).equals(HETROZYGOUS), "Zygosity of the Variant "+TTLL10+" is not HOMOZYGOUS")
        verifyUtil.verify(getMotherZygosity(TTLL10).equals(HETROZYGOUS), "Mother Zygosity of the Variant "+TTLL10+" is not "+HETROZYGOUS)
        verifyUtil.verify(getFatherZygosity(TTLL10).equals(NONE), "Father Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getSiblingZygosity(TTLL10).equals(NONE), "Sibling Zygosity of the Variant "+TTLL10+" is showing")
        verifyUtil.verify(getQualityGQCoverage(TTLL10).equals(true), "Quality GQ Coverage of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getOmiciaScore(TTLL10).equals(true), "Omicia Score of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getScoreContainer(TTLL10).equals(true), "Score Container of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getEvidence(TTLL10).equals(System.getProperty("geb.build.baseUrl")+"static/img/evidence_icons/clinvar.png"), "Evidence of the Variant "+TTLL10+" is not matching")
        verifyUtil.verify(getClassBasedOnVariant(TTLL10).equals(NONE_TEXT),"CLASS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getStatusBasedOnVariant(TTLL10).equals(NONE_TEXT),"STATUS of Variant: "+TTLL10+" is not equal to "+NONE_TEXT)
        verifyUtil.verify(getVAASTGeneRankBasedOnVariant(TTLL10).equals(ONE),"VAAST Gene Rank of Variant: "+TTLL10+" is not equal to "+ONE)
        verifyUtil.verify(getPhevorRankBasedOnVariant(TTLL10).equals(THREE),"Phevor Rank of Variant: "+TTLL10+" is not equal to "+THREE)
        verifyUtil.verify(getInheritanceMode(TTLL10).equals(RECESSIVE),"Inheritance Mode of Variant: "+TTLL10+" is not equal to "+ RECESSIVE)
        clickOnInterpretVariantBasedOnVariant(TTLL10)

        at VariantInterpretEditPage
        editVariant(CLASSIFICATION_PATHOGENIC, PRIMARY_FINDING)
        saveVariant()
        closeInterpretVariantDialog()

        at VariantInterpretationHomePage
        clickReviewReport()

        at ReviewReportPage
        verifyUtil.verify(getNumberOfPrimaryFindingReports().equals(ONE),"Primary Finding Reports size is not equal to "+ONE)
        verifyUtil.verify(getResponseCodeForPreviewPDF().equals(200),"Response code for the Preview PDF is not equal to 200")
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodExecution(){
        verifyUtil.assertTestResult("Test Case '"+currentMethod+"' Assertions Failed :")
    }
}