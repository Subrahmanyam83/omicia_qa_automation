package Specs.Smoke

import Pages.Clinical_Reporter.ClinicalReporterPage
import Pages.Clinical_Reporter.InterpretVariantsPage
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
import Pages.Projects.VariantReportPage
import Pages.Upload_Genomes.UploadGenomePage
import Specs.Smoke.TestData.SmokeTestData
import Utilities.Class.BaseSpec
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by E002183 on 4/21/2016.
 */
class SmokeSpec extends BaseSpec{

    SmokeTestData smokeData = new SmokeTestData();

    @Test
    public void BasicOpalFunctionality() {

        to LoginPage
        signIn();

        at OmiciaHomePage
        openTab(UPLOAD_GENOMES);

        at UploadGenomePage
        fillUploadGenomeForm(smokeData.PROJECT_NAME, true, true, smokeData.SHORT_FILE);
        waitForTheVCFFileToUpload();
        fillUploadGenomeForm(smokeData.PROJECT_NAME, false, true, smokeData.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        clickOnMenuAndSelectOption(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(smokeData.PROJECT_NAME, smokeData.GENE_COUNT)
        clickProjectInProjectsHomePage(smokeData.PROJECT_NAME);

        at ProjectsPage
        clickOnColumnBasedOnGenomeLabel(smokeData.GENE_LABEL, REPORT_TYPE)

        at VariantReportPage
        getNumberOfGenesOnVariantPage().equals(smokeData.VARIANT_GENE)
        clickOnColumnBasedOnGene(smokeData.GENE_NAME, EFFECT)
        verifyDialogBoxPresentOnClickAndClose()
        clickOnColumnBasedOnGene(smokeData.GENE_NAME, ONE_KG_AF)
        verifyDialogBoxPresentOnClickAndClose()
        clickOnColumnBasedOnGene(smokeData.GENE_NAME, EVIDENCE_CV)
        verifyDialogBoxPresentOnClickAndClose();

        at HeaderPage
        clickOnMenuAndSelectOption(GENE_SETS)

        at GeneSetsPage
        createNewGeneSet(smokeData.GENE_SET_NAME, smokeData.DESCRIPTION, MY_SET);
        performActionBasedOnGeneSetName(MY_SET, smokeData.GENE_SET_NAME, ADD_GENES);
        addGenesToGeneSet(smokeData.GENE_SYMBOLS);

        at HeaderPage
        clickOnMenuAndSelectOption(PANEL_BUILDER)

        at PanelBuilderPage
        createNewPanel(smokeData.PANEL_NAME,smokeData.PANEL_DESCRIPTION)
        clickOnActionsButtonBasedOnAndClickAction(smokeData.PANEL_NAME, CURATE_PANEL)

        at CuratePanelPage
        chooseFilter(PROTIEN_FILTER);
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        addGenesByGivingSymbols(smokeData.GENES_TO_BE_ADDED_AS_SYMBOLS)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        waitFor { curatePanel.getNumberOfPanelGenes }
        Assert.assertEquals(getNumberOfPanelGenes(), THREE)

        at HeaderPage
        clickOnMenuAndSelectOption(FILTERING_PROTOCOL)

        at NewFilteringProtocolPage
        createNewFilteringProtocol()
        fillNameAndDescription(smokeData.FILTERING_PROTOCOL_NAME, smokeData.FILTERING_PROTOCOL_DESCRIPTION)
        chooseConsequenceGenemodelAndExclude(CONSEQUENCE_LIST, [], EVIDENCE_LIST)
        saveFilteringProtocol()

        at HeaderPage
        clickOnMenuAndSelectOption(CLINICAL_REPORTER)

        at ClinicalReporterPage
        clickOnNewReportAndSelectDropDownValue(PANEL)
        createNewPanelReport(smokeData.PATIENT_ID, smokeData.PANEL_NAME, smokeData.FILTERING_PROTOCOL_NAME, smokeData.PROJECT_NAME)
        selectGenomeFromGenomeList(smokeData.GENOME_NAME_TO_BE_SELECTED)
        selectSaveNewPanelReport()
        refreshTillStatusChangesToReadyForInterpretation(smokeData.PATIENT_ID)
        clickOnActionsAndValueBasedOnPatientId(smokeData.PATIENT_ID, INTERPRET_VARIANTS)

        at InterpretVariantsPage
        Assert.equals(getEffectBasedOnGene(SAMD11).equals(MISSENSE))
    }


}
