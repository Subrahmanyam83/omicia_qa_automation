package Specs.OMICIA

import Pages.Gene_Sets.GeneSetsPage
import Pages.Login.HeaderPage
import Pages.Login.LoginPage
import Pages.Login.OmiciaHomePage
import Pages.Panel_Builder.AddGenesToPanelPage
import Pages.Panel_Builder.CuratePanelPage
import Pages.Panel_Builder.PanelBuilderPage
import Pages.Projects.ProjectsHomePage
import Pages.Projects.ProjectsPage
import Pages.UploadGenomes.UploadGenomePage
import Pages.Projects.VariantReportPage
import Specs.TestData.SmokeTestData
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
        fillUploadGenomeForm(smokeData.NEW_PROJECT_NAME, true, true, smokeData.SHORT_FILE);
        waitForTheVCFFileToUpload();
        fillUploadGenomeForm(smokeData.NEW_PROJECT_NAME, false, true, smokeData.FOUR_EXOMES);
        waitForTheVCFFileToUpload();

        at HeaderPage
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openTab(PROJECTS)

        at ProjectsHomePage
        refreshTillCountMatches(smokeData.NEW_PROJECT_NAME, smokeData.GENE_COUNT)
        clickProjectInProjectsHomePage(smokeData.NEW_PROJECT_NAME);

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
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openTab(GENE_SETS)

        at GeneSetsPage
        createNewGeneSet(smokeData.GENE_SET_NAME, smokeData.DESCRIPTION, MY_SET);
        performActionBasedOnGeneSetName(EXCLUDE_SET,smokeData.GENE_SET_NAME,ADD_GENES);
        addGenesToGeneSet(smokeData.GENE_SYMBOLS);

        at HeaderPage
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openTab(PANEL_BUILDER)

        at PanelBuilderPage
        createNewPanel(smokeData.PANEL_NAME,smokeData.PANEL_DESCRIPTION)
        clickOnActionsButtonAndClickAction(smokeData.PANEL_NAME,CURATE_PANEL)

        at CuratePanelPage
        chooseFilter(PROTIEN_FILTER);
        clickOnCuratePanelHeaderButton(ADD_GENE);

        at AddGenesToPanelPage
        clickOnTab(FROM_HPO_TERMS_AND_PHEVOR)
        typeAndSelectTextOnRunPhevorField(RUN_PHEVOR_TEXT_VALUE)
        clickOnButton(RUN_PHEVOR)
        clickOnGenes(smokeData.GENES_TO_BE_CHECKED)
        clickOnButton(ADD_GENE)
        clickOnButton(BACK)

        at CuratePanelPage
        Assert.assertEquals(getNumberOfPanelGenes(),TWO)

        at HeaderPage
        header.homePageHeaderOmiciaText.click()

        at OmiciaHomePage
        openTab(FILTERING_PROTOCOL);
    }


}
